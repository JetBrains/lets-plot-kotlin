/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.PlotSizeHelper
import jetbrains.datalore.plot.config.PlotConfig
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import kotlin.math.ceil
import kotlin.math.floor

/**
 *  Arrange plots in cells of a regular grid.
 *
 *  @param plots Collection of plots.
 *  @param ncol Number of columns in grid.
 *  @param cellWidth Width of cell in px.
 *  @param cellHeight Height of cell in px.
 *  @param hGap Horizontal gap between the grid cells in px. Default: 0
 *  @param vGap Vertical gap between the grid cells in px. Default: 50
 *  @param cellHeight Height of cell in px.
 *  @param fit Whether to set size of each plot to the size of grid cell. Default: false.
 *  @return GGBunch object.
 */
@Suppress("SpellCheckingInspection")
fun gggrid(
    plots: Iterable<Plot>,
    ncol: Int,
    cellWidth: Int, cellHeight: Int,
    hGap: Int = 0, vGap: Int = 50,
    fit: Boolean = false
): GGBunch {
    val bunch = GGBunch()
    for ((i, p) in plots.withIndex()) {
        val figureSize = preferredFigureSize(p.toSpec(), cellWidth, cellHeight)

        val col = i % ncol
        val row = i / ncol
        val x = col * (cellWidth + hGap)
        val y = row * (cellHeight + vGap)
        if (fit) {
            bunch.addPlot(p, x, y, cellWidth, cellHeight)
        } else {
            bunch.addPlot(p, x, y, figureSize.first, figureSize.second)
        }
    }
    return bunch
}

// ToDo: move to the main lib.
private fun preferredFigureSize(figureSpec: Map<String, Any>, width: Int, height: Int): Pair<Int, Int> {
    return when {
        PlotConfig.isGGBunchSpec(figureSpec) -> {
            // don't scale GGBunch size
            val bunchSize = PlotSizeHelper.plotBunchSize(figureSpec)
            Pair(ceil(bunchSize.x).toInt(), ceil(bunchSize.y).toInt())
        }
        PlotConfig.isPlotSpec(figureSpec) -> {
            // for single plot: scale component to fit in requested size
            val aspectRatio = PlotSizeHelper.figureAspectRatio(figureSpec)
            if (aspectRatio >= 1.0) {
                val plotHeight = width / aspectRatio
                val scaling = if (plotHeight > height) height / plotHeight else 1.0
                Pair(floor(width * scaling).toInt(), floor(plotHeight * scaling).toInt())
            } else {
                val plotWidth = height * aspectRatio
                val scaling = if (plotWidth > width) width / plotWidth else 1.0
                Pair(floor(plotWidth * scaling).toInt(), floor(height * scaling).toInt())
            }
        }
        else ->
            // was failure - just keep given size
            Pair(width, height)
    }
}
