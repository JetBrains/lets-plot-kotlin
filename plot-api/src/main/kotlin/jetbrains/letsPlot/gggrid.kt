/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.Plot

/**
 *  Arrange plots in cells of a regular grid.
 *
 *  @param plots Collection of plots.
 *  @param ncol Number of columns in grid.
 *  @param cellWidth Width of cell in px.
 *  @param cellHeight Height of cell in px.
 *  @param fit Whether to set size of each plot to the size of grid cell. Default: false.
 *  @return GGBunch object.
 */
@Suppress("SpellCheckingInspection")
fun gggrid(plots: Iterable<Plot>, ncol: Int, cellWidth: Int, cellHeight: Int, fit: Boolean = false): GGBunch {
    val bunch = GGBunch()
    for ((i, p) in plots.withIndex()) {
        val col = i % ncol
        val row = i / ncol
        if (fit) {
            bunch.addPlot(p, col * cellWidth, row * cellHeight, cellWidth, cellHeight)
        } else {
            bunch.addPlot(p, col * cellWidth, row * cellHeight)
        }
    }
    return bunch
}