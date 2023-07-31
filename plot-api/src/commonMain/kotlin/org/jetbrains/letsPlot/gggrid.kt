/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option.SubPlots
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.figure.SubPlotsLayoutSpec
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 *  Combines several plots on one figure, organized in a regular grid.
 *
 * ## Examples
 *
 * - [plot_grid.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/plot_grid.ipynb)
 *
 *  @param plots Collection of plots.
 *   Use Null-value to fill-in empty cells in grid.
 *  @param ncol Number of columns in grid.
 *   If not specified, shows plots horizontally, in one row.
 *  @param widths Relative width of each column of grid, left to right.
 *  @param heights Relative height of each row of grid, top-down.
 *  @param hspace default = 4. Cell horizontal spacing in px.
 *  @param vspace default = 4. Cell vertical spacing in px.
 *  @param fit default = true.
 *   Whether to stretch each plot to match the aspect ratio of its cell (`fit = true`),
 *   or to preserve the original aspect ratio of plots (`fit = false`).
 *  @param align default = false.
 *   If `true`, align inner areas (i.e. "geom" bounds) of plots.
 *   However, cells containing other (sub)grids are not participating in the plot "inner areas" layouting.
 *
 *  @return SubPlotsFigure object.
 */
@Suppress("SpellCheckingInspection")
fun gggrid(
    plots: Iterable<Figure?>,
    ncol: Int? = null,
    widths: List<Number>? = null,
    heights: List<Number>? = null,
    hspace: Number? = null,
    vspace: Number? = null,
    fit: Boolean = true,
    align: Boolean = false,

    ): SubPlotsFigure {

    val (nc, nrow) = plots.toList().let {
        require(it.isNotEmpty()) { "Supplots list is empty." }

        if (ncol == null) {
            it.size to 1
        } else {
            val nrow = ((it.size + (ncol - 1)) / ncol)
            ncol to nrow
        }
    }

    @Suppress("NAME_SHADOWING")
    val ncol = nc

    val layout = SubPlotsLayoutSpec(
        name = SubPlots.Layout.SUBPLOTS_GRID,
        options = mapOf(
            SubPlots.Grid.NCOLS to ncol,
            SubPlots.Grid.NROWS to nrow,
            SubPlots.Grid.COL_WIDTHS to widths,
            SubPlots.Grid.ROW_HEIGHTS to heights,
            SubPlots.Grid.HSPACE to hspace,
            SubPlots.Grid.VSPACE to vspace,
            SubPlots.Grid.FIT_CELL_ASPECT_RATIO to fit,
            SubPlots.Grid.INNER_ALIGNMENT to align,
        ).filterNonNullValues()
    )

    val len = ncol * nrow

    @Suppress("NAME_SHADOWING")
    val plots = (plots.toList() + List(ncol - 1) { null }).take(len)

    return SubPlotsFigure(
        figures = plots,
        layout = layout,
        features = emptyList()
    )
}
