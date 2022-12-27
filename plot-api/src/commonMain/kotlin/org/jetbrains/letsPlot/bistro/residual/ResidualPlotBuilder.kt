/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.base.values.Color
import jetbrains.datalore.plot.base.data.DataFrameUtil
import org.jetbrains.letsPlot.geom.geomBin2D
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.geom.geomHLine
import org.jetbrains.letsPlot.geom.geomHistogram
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggmarginal
import org.jetbrains.letsPlot.intern.DummyFeature
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.GenericAesMapping
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.asPlotData
import org.jetbrains.letsPlot.label.ylab
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.elementBlank
import org.jetbrains.letsPlot.themes.elementLine
import org.jetbrains.letsPlot.themes.elementText
import org.jetbrains.letsPlot.themes.theme
import kotlin.math.max

internal class ResidualPlotBuilder(
    data: Map<*, *>,
    private val x: String,
    private val y: String,
    private val method: String?,
    private val polynomialDegree: Int?,
    private val span: Double?,
    private val loessCriticalSize: Int,
    private val samplingSeed: Long,
    private val geom: String?,
    private val bins: Any?,
    private val binWidth: Any?,
    private val color: String?,
    private val size: Number?,
    private val alpha: Number?,
    private val colorBy: String?,
    private val showLegend: Boolean,
    private val hline: Boolean,
    private val marginal: String?
) {
    private val myData = asPlotData(data)

    fun build(): Plot {
        val statData = ResidualUtil.appendResiduals(myData, x, y, getModel(), loessCriticalSize, samplingSeed)

        val mapping: GenericAesMapping.() -> Unit = {
            x = this@ResidualPlotBuilder.x
            y = ResidualVar.RESIDUAL
            color = colorBy ?: color
        }

        val themeOptions = theme(
            axis = elementBlank(),
            axisTextX = elementText(),
            axisTitleX = elementText(),
            axisLineY = elementLine(),
            axisTicksY = elementLine(),
            axisTextY = elementText(),
            axisTitleY = elementText()
        )

        return letsPlot(statData, mapping) + layers() + scales() + themeOptions
    }

    private fun getModel(): Model {
        return Model(
            method = if (method != null) Model.Method.safeValueOf(method) else Model.METHOD_DEF,
            polynomialDegree = polynomialDegree ?: Model.POLYNOMIAL_DEGREE_DEF,
            span = span ?: Model.SPAN_DEF
        )
    }

    private fun scales(): Feature {
        val scaleLabel = when (method) {
            "none" -> y
            else -> "$y residual"
        }
        return ylab(scaleLabel)
    }

    private fun layers(): Feature {
        var layers: Feature = DummyFeature

        val bins: Pair<Int, Int>? = toPairOfNumbers(bins)?.let { it.first.toInt() to it.second.toInt() }
        val binWidth: Pair<Number, Number>? = run {
            val df = DataFrameUtil.fromMap(myData)
            val xVar = DataFrameUtil.findVariableOrFail(df, x)
            val yVar = DataFrameUtil.findVariableOrFail(df, y)
            getBinWidth(
                xs = df.getNumeric(xVar),
                ys = df.getNumeric(yVar),
                binWidth = toPairOfNumbers(binWidth),
                bins = bins
            )
        }

        // main layer
        when (geom) {
            "point" -> {
                layers += geomPoint(color = color, size = size, alpha = alpha, showLegend = showLegend)
            }
            "tile" -> {
                layers += geomBin2D(
                    bins = bins,
                    binWidth = binWidth,
                    color = color,
                    size = size,
                    alpha = alpha,
                    showLegend = showLegend
                )
            }
            "none" -> {}
            else -> throw IllegalArgumentException("Unknown geom: $geom")
        }

        // HLine
        if (hline) {
            layers += geomHLine(yintercept = 0, color = Color.MAGENTA, linetype = "dashed")
        }

        // marginals
        if (marginal != "none") {
            layers += parseMarginal(marginal, color, colorBy, showLegend, bins, binWidth)
        }

        return layers
    }

    private fun parseMarginal(
        marginal: String?,
        color: String?,
        colorBy: String?,
        showLegend: Boolean,
        bins2d: Pair<Int, Int>?,
        binWidth2d: Pair<Number, Number>?
    ): Feature {
        fun parseMarginalLayer(geomName: String, side: Char, size: Any?): Feature {
            val layer = when (geomName) {
                in listOf("dens", "density") -> {
                    geomDensity(color = color, showLegend = showLegend)
                }
                in listOf("hist", "histogram") -> {
                    val bins = when {
                        bins2d == null -> null
                        side == 't' || side == 'b' -> bins2d.first
                        else -> bins2d.second
                    }
                    val binWidth = when {
                        binWidth2d == null -> null
                        side == 't' || side == 'b' -> binWidth2d.first
                        else -> binWidth2d.second
                    }
                    val marginalColor = when {
                        colorBy != null -> null
                        color != null -> color
                        else -> DEF_COLOR
                    }
                    geomHistogram(
                        color = marginalColor,
                        alpha = 0,
                        bins = bins,
                        binWidth = binWidth,
                        showLegend = showLegend
                    )
                }
                in listOf("box", "boxplot") -> {
                    geomBoxplot(color = color, showLegend = showLegend)
                }
                else -> {
                    throw IllegalArgumentException("Unknown geom: $geomName")
                }
            }
            return ggmarginal(side.toString(), size = size, layer = layer)
        }

        var marginals: Feature = DummyFeature
        marginal?.split(",")?.forEach { layerDescription ->
            val params = layerDescription.trim().split(":")
            val geomName = params[0]
            val sides = params.getOrNull(1)
            val size = params.getOrNull(2)?.toDouble()
            sides?.forEach { side ->
                marginals += parseMarginalLayer(geomName, side.lowercaseChar(), size)
            }
        }
        return marginals
    }

    private fun toPairOfNumbers(bins: Any?): Pair<Number, Number>? {
        return when (bins) {
            is Number -> bins to bins
            is Pair<*, *> -> {
                val first = (bins.first as? Number)
                val second = (bins.second as? Number)
                if (first != null && second != null) {
                    first to second
                } else {
                    null
                }
            }
            else -> null
        }
    }

    private fun getBinWidth(
        xs: List<Double?>,
        ys: List<Double?>,
        binWidth: Pair<Number, Number>?,
        bins: Pair<Int, Int>?
    ): Pair<Number, Number>? {
        if (binWidth != null || bins != null) {
            return binWidth
        }

        val binWidthX = with(xs.filterNotNull()) {
            (max() - min()) / DEF_BINS
        }
        val binWidthY = with(ys.filterNotNull()) {
            (max() - min()) / DEF_BINS
        }
        val binWidthMax = max(binWidthX, binWidthY)
        return binWidthMax to binWidthMax
    }

    companion object {
        const val LOESS_CRITICAL_SIZE_DEF = 1_000
        const val SAMPLING_SEED_DEF = 37L
        const val DEF_BINS = 30
        const val DEF_GEOM = "point"
        const val DEF_MARGINAL = "dens:r"
        const val DEF_COLOR = "#118ed8"
    }
}