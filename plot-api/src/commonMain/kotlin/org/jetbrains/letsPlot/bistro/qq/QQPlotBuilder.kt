/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.qq

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.QQPlotOptionsBuilder.Companion.DEF_DISTRIBUTION
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.QQPlotOptionsBuilder.Companion.DEF_LINE_COLOR
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.QQPlotOptionsBuilder.Companion.DEF_LINE_SIZE
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.QQPlotOptionsBuilder.Companion.DEF_POINT_ALPHA
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.QQPlotOptionsBuilder.Companion.DEF_POINT_SIZE
import org.jetbrains.letsPlot.geom.geomQQ
import org.jetbrains.letsPlot.geom.geomQQ2
import org.jetbrains.letsPlot.geom.geomQQ2Line
import org.jetbrains.letsPlot.geom.geomQQLine
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.GenericAesMapping
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.asPlotData
import org.jetbrains.letsPlot.intern.layer.stat.QQ2StatMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQStatMapping
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleColorDiscrete
import org.jetbrains.letsPlot.scale.scaleFillDiscrete

internal class QQPlotBuilder(
    private val data: Map<*, *>,
    private val sample: String? = null,
    private val x: String? = null,
    private val y: String? = null,
    distribution: String? = null,
    private val dParams: List<Number>? = null,
    private val quantiles: Pair<Number, Number>? = null,
    private val group: String? = null,
    private val showLegend: Boolean = true,
    private val manualKey: Any? = null,
    private val color: String? = null,
    private val fill: String? = null,
    alpha: Number? = null,
    size: Number? = null,
    private val shape: Int? = null,
    lineColor: String? = null,
    lineSize: Number? = null,
    private val linetype: Any? = null
) {
    private val myDistribution = distribution ?: DEF_DISTRIBUTION
    private val myLineColor = lineColor ?: if (group == null) DEF_LINE_COLOR else null
    private val myAlpha = alpha ?: DEF_POINT_ALPHA
    private val mySize = size ?: DEF_POINT_SIZE
    private val myLineSize = lineSize ?: DEF_LINE_SIZE

    fun build(): Plot {
        val mapping: GenericAesMapping.() -> Unit = {
            if (this@QQPlotBuilder.group != null) {
                group = this@QQPlotBuilder.group
                color = this@QQPlotBuilder.group
                fill = this@QQPlotBuilder.group
            }
        }
        return letsPlot(asPlotData(data), mapping) + layers() + scales()
    }

    private fun layers() = if (sample != null) qqLayers() else qq2Layers()

    private fun qqLayers(): Feature {
        val mapping = qqMapping()
        return geomQQ(
            showLegend = showLegend,
            manualKey = manualKey,
            color = color,
            fill = fill,
            alpha = myAlpha,
            size = mySize,
            shape = shape,
            stat = Stat.qq(myDistribution, dParams, mapping)
        ) + geomQQLine(
            showLegend = showLegend,
            manualKey = manualKey,
            color = myLineColor,
            size = myLineSize,
            linetype = linetype,
            stat = Stat.qqLine(myDistribution, dParams, quantiles, mapping)
        )
    }

    private fun qq2Layers(): Feature {
        val mapping = qq2Mapping()
        return geomQQ2(
            showLegend = showLegend,
            manualKey = manualKey,
            color = color,
            fill = fill,
            alpha = myAlpha,
            size = mySize,
            shape = shape,
            stat = Stat.qq2(mapping)
        ) + geomQQ2Line(
            showLegend = showLegend,
            manualKey = manualKey,
            color = myLineColor,
            size = myLineSize,
            linetype = linetype,
            stat = Stat.qq2Line(quantiles, mapping)
        )
    }

    private fun qqMapping(): QQStatMapping.() -> Unit {
        require(x == null)
        { "Parameter x shouldn't be specified when parameter sample is." }
        require(y == null)
        { "Parameter y shouldn't be specified when parameter sample is." }

        val mapping: QQStatMapping.() -> Unit = {
            sample = this@QQPlotBuilder.sample
        }
        return mapping
    }

    private fun qq2Mapping(): QQ2StatMapping.() -> Unit {
        require(x != null)
        { "Parameter x should be specified when parameter sample isn't." }
        require(y != null)
        { "Parameter y should be specified when parameter sample isn't." }

        val mapping: QQ2StatMapping.() -> Unit = {
            x = this@QQPlotBuilder.x
            y = this@QQPlotBuilder.y
        }
        return mapping
    }

    private fun scales(): Feature {
        val scaleNames = mapOf(
            Aes.X to if (sample != null) "\"$myDistribution\" distribution quantiles" else "$x quantiles",
            Aes.Y to if (sample != null) "$sample quantiles" else "$y quantiles",
        )
        return labs(
            x = scaleNames[Aes.X],
            y = scaleNames[Aes.Y]
        ) + scaleColorDiscrete() + scaleFillDiscrete()
    }
}