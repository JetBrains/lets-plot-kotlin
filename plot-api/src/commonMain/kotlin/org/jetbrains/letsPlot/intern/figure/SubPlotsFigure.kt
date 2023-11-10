/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.figure

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.core.spec.Option.Meta
import org.jetbrains.letsPlot.core.spec.Option.Plot
import org.jetbrains.letsPlot.core.spec.Option.SubPlots
import org.jetbrains.letsPlot.core.spec.Option.SubPlots.LAYOUT
import org.jetbrains.letsPlot.frontend.CurrentFrontendContext
import org.jetbrains.letsPlot.intern.*

/**
 * Combines several plots on one figure.
 */
class SubPlotsFigure(
    private val figures: List<Figure?>,
    private val layout: SubPlotsLayoutSpec,
    internal val features: List<OptionsMap>,
) : Figure {

    operator fun plus(other: Feature): SubPlotsFigure {
        return when (other) {
            is DummyFeature -> this  // nothing
            is FeatureList -> withFeatureList(this, other)
            else -> withFeature(this, other)
        }
    }

    fun toSpec(): MutableMap<String, Any> {
        val spec = mutableMapOf(
            Meta.KIND to Meta.Kind.SUBPLOTS,
            SubPlots.FIGURES to figures.map { it?.toSpec() },
            LAYOUT to layout.toSpec(),
        )

        val themeOptionList = features.filter { it.kind == Plot.THEME }

        // Merge themes
        ThemeOptionsUtil.toSpec(themeOptionList)?.let {
            spec[Plot.THEME] = it
        }

        @Suppress("ConvertArgumentToSet")
        (features - themeOptionList).forEach {
            spec[it.kind] = it.toSpec()
        }

        return spec
    }

    override fun show() {
        CurrentFrontendContext.display(this.toSpec())
    }

    companion object {
        private fun withFeature(self: SubPlotsFigure, feature: Feature): SubPlotsFigure {
            require(feature is OptionsMap) { "plot grid: unsupported feature $feature" }
            require(feature.kind in listOf(Plot.SIZE, Plot.THEME)) {
                "plot grid: unsupported feature ${feature.kind}"
            }

            return SubPlotsFigure(
                figures = self.figures,
                layout = self.layout,
                features = self.features + listOf(feature)
            )
        }

        private fun withFeatureList(self: SubPlotsFigure, featureList: FeatureList): SubPlotsFigure {
            val features = featureList.elements.map { feature ->
                require(feature is OptionsMap) { "plot grid: unsupported feature $feature" }
                require(feature.kind in listOf(Plot.SIZE, Plot.THEME)) {
                    "plot grid: unsupported feature ${feature.kind}"
                }
                feature
            }

            return SubPlotsFigure(
                figures = self.figures,
                layout = self.layout,
                features = self.features + features
            )
        }
    }
}