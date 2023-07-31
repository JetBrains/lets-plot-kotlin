/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.common

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.core.plot.base.data.DataFrameUtil
import org.jetbrains.letsPlot.geom.*
import org.jetbrains.letsPlot.ggmarginal
import org.jetbrains.letsPlot.intern.DummyFeature
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.pos.positionIdentity
import kotlin.math.max

object Plot2dUtil {

    private const val DEF_BINS = 30
    private const val MARGINAL_ALPHA = 0.1
    private const val DEF_COLOR = "#118ed8"

    fun getBinParams2d(
        data: Map<String, List<Any?>>,
        x: String,
        y: String,
        bins: Any?,
        binWidth: Any?
    ): Pair<Pair<Int, Int>?, Pair<Number, Number>?> {
        val bins2d: Pair<Int, Int>? = getBins2d(bins)
        val binWidth2d: Pair<Number, Number>? = getBinWidth2d(data, x, y, binWidth, bins2d)
        return bins2d to binWidth2d
    }

    private fun getBins2d(bins: Any?): Pair<Int, Int>? {
        return toPairOfNumbers(bins)?.let { it.first.toInt() to it.second.toInt() }
    }

    private fun getBinWidth2d(
        data: Map<String, List<Any?>>,
        x: String,
        y: String,
        binWidth: Any?,
        bins2d: Pair<Int, Int>?
    ): Pair<Number, Number>? {
        val df = DataFrameUtil.fromMap(data)
        val xVar = DataFrameUtil.findVariableOrFail(df, x)
        val yVar = DataFrameUtil.findVariableOrFail(df, y)

        val xs = df.getNumeric(xVar)
        val ys = df.getNumeric(yVar)

        val binWidth2d = toPairOfNumbers(binWidth)
        if (binWidth2d != null || bins2d != null || xs.isEmpty()) {
            return binWidth2d
        }

        val binWidthX = with(xs.filterNotNull()) {
            if (isEmpty()) null else (max() - min()) / DEF_BINS
        }
        val binWidthY = with(ys.filterNotNull()) {
            if (isEmpty()) null else (max() - min()) / DEF_BINS
        }
        if (binWidthX == null || binWidthY == null) {
            return null
        }
        val binWidthMax = max(binWidthX, binWidthY)
        return binWidthMax to binWidthMax
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

    fun getGeom2dLayer(
        geomKind: String?,
        bins2d: Pair<Int, Int>?,
        binWidth2d: Pair<Number, Number>?,
        color: String?,
        size: Number?,
        alpha: Number?,
        colorBy: String?,
        showLegend: Boolean,
    ): Feature {

        return when (geomKind) {
            "point" -> {
                geomPoint(color = color, size = size, alpha = alpha, showLegend = showLegend)
            }

            "tile" -> {
                geomBin2D(
                    bins = bins2d,
                    binWidth = binWidth2d,
                    color = color,
                    size = size,
                    alpha = alpha,
                    showLegend = showLegend
                ) {
                    fill = colorBy.takeIf { it != null } ?: "..count.."
                }
            }

            "density2d" -> {
                geomDensity2D(
                    color = color,
                    size = size,
                    alpha = alpha,
                    showLegend = showLegend
                ) {
                    this.color = colorBy.takeIf { it != null } ?: "..group.."
                }
            }

            "density2df" -> {
                geomDensity2DFilled(
                    color = color,
                    size = size,
                    alpha = alpha,
                    showLegend = showLegend
                ) {
                    fill = colorBy.takeIf { it != null } ?: "..group.."
                }
            }

            "none" -> DummyFeature

            else -> throw IllegalArgumentException("Unknown geom: $geomKind")
        }
    }

    fun getMarginalLayers(
        marginal: String,
        bins2d: Pair<Int, Int>?,
        binWidth2d: Pair<Number, Number>?,
        color: String?,
        colorBy: String?,
        showLegend: Boolean
    ): Feature {
        fun parseMarginalLayer(geomKind: String, side: Char, size: Any?): Feature {
            val marginalColor = when {
                colorBy != null -> null
                color != null -> color
                else -> DEF_COLOR
            }
            val layer = when (geomKind) {
                in listOf("dens", "density") -> {
                    geomArea(
                        stat = Stat.density(),
                        position = positionIdentity,
                        color = marginalColor,
                        fill = marginalColor,
                        alpha = MARGINAL_ALPHA,
                        showLegend = showLegend
                    )
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
                    geomHistogram(
                        color = marginalColor,
                        fill = marginalColor,
                        alpha = MARGINAL_ALPHA,
                        bins = bins,
                        binWidth = binWidth,
                        showLegend = showLegend
                    )
                }

                in listOf("box", "boxplot") -> {
                    geomBoxplot(
                        color = marginalColor,
                        fill = marginalColor,
                        alpha = MARGINAL_ALPHA,
                        showLegend = showLegend
                    )
                }

                else -> {
                    throw IllegalArgumentException("Unknown geom: $geomKind")
                }
            }
            return ggmarginal(side.toString(), size = size, layer = layer)
        }

        var marginals: Feature = DummyFeature
        marginal.split(",").forEach { layerDescription ->
            val params = layerDescription.trim().split(":")
            val geomKind = params[0].trim()
            val sides = params.getOrNull(1)?.trim()
            val size = params.getOrNull(2)?.trim()?.toDouble()
            sides?.forEach { side ->
                marginals += parseMarginalLayer(geomKind, side.lowercaseChar(), size)
            }
        }
        return marginals
    }
}