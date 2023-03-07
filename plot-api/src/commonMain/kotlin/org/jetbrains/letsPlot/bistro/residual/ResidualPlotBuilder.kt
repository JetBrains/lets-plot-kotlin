/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.base.values.Color
import org.jetbrains.letsPlot.bistro.common.Plot2dUtil
import org.jetbrains.letsPlot.geom.geomHLine
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
    private val marginal: String
) {
    private val myData = asPlotData(data)

    fun build(): Plot {
        val statData = ResidualUtil.appendResiduals(myData, x, y, colorBy, getModel(), loessCriticalSize, samplingSeed)

        val mapping: GenericAesMapping.() -> Unit = {
            x = this@ResidualPlotBuilder.x
            y = ResidualVar.RESIDUAL
            if (colorBy != null) {
                color = colorBy
                fill = colorBy
            }
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
        val (bins2d, binWidth2d) = Plot2dUtil.getBinParams2d(myData, x, y, bins, binWidth)

        var layers: Feature = DummyFeature

        // main layer
        layers += Plot2dUtil.getGeom2dLayer(
            geom,
            bins2d,
            binWidth2d,
            color,
            size,
            alpha,
            colorBy,
            showLegend
        )

        // HLine
        if (hline) {
            layers += geomHLine(yintercept = 0, color = HLINE_COLOR, linetype = HLINE_LINETYPE)
        }

        // marginals
        if (marginal != "none") {
            layers += Plot2dUtil.getMarginalLayers(marginal, bins2d, binWidth2d, color, colorBy, showLegend)
        }

        return layers
    }

    companion object {
        const val LOESS_CRITICAL_SIZE_DEF = 1_000
        const val SAMPLING_SEED_DEF = 37L
        const val DEF_GEOM = "point"
        const val DEF_MARGINAL = "dens:r"
        val HLINE_COLOR = Color.MAGENTA
        const val HLINE_LINETYPE = "dashed"
    }
}