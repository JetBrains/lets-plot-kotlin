/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.PlotSizeHelper
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.toSpec

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
@Deprecated(
    "Please replace with new version of `gggrid()` function.",
    ReplaceWith("gggrid(plots, ncol)"),
    level = DeprecationLevel.WARNING
)
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
        val col = i % ncol
        val row = i / ncol
        val x = col * (cellWidth + hGap)
        val y = row * (cellHeight + vGap)
        if (fit) {
            bunch.addPlot(p, x, y, cellWidth, cellHeight)
        } else {
            val figureSize = PlotSizeHelper.scaledFigureSize(p.toSpec(), cellWidth, cellHeight)
            bunch.addPlot(p, x, y, figureSize.first, figureSize.second)
        }
    }
    return bunch
}
