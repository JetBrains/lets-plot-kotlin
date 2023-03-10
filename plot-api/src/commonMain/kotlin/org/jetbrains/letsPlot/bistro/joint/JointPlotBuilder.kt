/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.joint

import org.jetbrains.letsPlot.bistro.common.Plot2dUtil
import org.jetbrains.letsPlot.geom.geomSmooth
import org.jetbrains.letsPlot.intern.DummyFeature
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.GenericAesMapping
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.asPlotData
import org.jetbrains.letsPlot.label.xlab
import org.jetbrains.letsPlot.label.ylab
import org.jetbrains.letsPlot.letsPlot

internal class JointPlotBuilder(
    data: Map<*, *>,
    private val x: String,
    private val y: String,
    geom: String?,
    private val bins: Any?,
    private val binWidth: Any?,
    private val color: String?,
    private val size: Number?,
    private val alpha: Number?,
    private val colorBy: String?,
    private val showLegend: Boolean,
    private val regLine: Boolean?,
    private val se: Boolean,
    private val marginal: String?
) {
    private val myData = asPlotData(data)
    private val myGeomKind = geom ?: DEF_GEOM

    fun build(): Plot {
        val mapping: GenericAesMapping.() -> Unit = {
            x = this@JointPlotBuilder.x
            y = this@JointPlotBuilder.y
            if (colorBy != null) {
                color = colorBy
                fill = colorBy
            }
        }

        return letsPlot(myData, mapping) + layers() + scales()
    }

    private fun scales() = xlab(x) + ylab(y)

    private fun layers(): Feature {
        val (bins2d, binWidth2d) = Plot2dUtil.getBinParams2d(myData, x, y, bins, binWidth)

        var layers: Feature = DummyFeature

        // main layer
        layers += Plot2dUtil.getGeom2dLayer(
            myGeomKind,
            bins2d,
            binWidth2d,
            color,
            size,
            alpha,
            colorBy,
            showLegend
        )

        // smooth layer
        if (regLine ?: (myGeomKind == "point")) {
            layers += geomSmooth(
                method = REG_LINE_METHOD,
                se = se,
                color = REG_LINE_COLOR,
                linetype = REG_LINE_LINETYPE
            ) {
                group = colorBy
            }
        }

        // marginals
        val definedMarginal = when {
            myData.containsKey(x) && myData[x]!!.isEmpty() -> "none"
            marginal != null -> marginal
            myGeomKind in listOf("density2d", "density2df") -> "dens:tr"
            colorBy != null -> "dens:tr"
            else -> "hist:tr"
        }
        if (definedMarginal != "none") {
            layers += Plot2dUtil.getMarginalLayers(
                definedMarginal,
                bins2d,
                binWidth2d,
                color,
                colorBy,
                showLegend
            )
        }

        return layers
    }

    companion object {
        const val DEF_GEOM = "point"
        const val REG_LINE_METHOD = "lm"
        const val REG_LINE_COLOR = "magenta"
        const val REG_LINE_LINETYPE = "dashed"
    }
}