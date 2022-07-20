/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.facet

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 *
 * Splits data by one or two faceting variables.
 * For each data subset creates a plot panel and lays out panels as grid.
 * The grid columns are defined by X faceting variable and rows are defined by Y faceting variable.
 *
 * ## Examples
 *
 * - [facets.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets.ipynb)
 *
 * - [facets_free_scales.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets_free_scales.ipynb)
 *
 * @param x Variable name which defines columns of the facet grid.
 * @param y Variable name which defines rows of the facet grid.
 * @param scales Specifies whether scales are shared across all facets.
 *      "fixed" - shared (the default), "free" - vary across both rows and columns,
 *      "free_x" or "free_y" - vary across rows or columns respectively.
 * @param xOrder Specifies ordering direction of columns: 1 - ascending, -1 - descending
 * @param yOrder Specifies ordering direction of rows: 1 - ascending, -1 - descending
 * @param xFormat Specifies the format pattern for displaying faceting values in columns.
 * @param yFormat Specifies the format pattern for displaying faceting values in rows.
 *
 * Format pattern in the xFormat/yFormat parameters can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 **
 */
fun facetGrid(
    x: String? = null,
    y: String? = null,
    scales: String? = null,
    xOrder: Int = 1,
    yOrder: Int = 1,
    xFormat: String? = null,
    yFormat: String? = null
): OptionsMap {
    return OptionsMap(
        Option.Plot.FACET,
        Option.Facet.NAME_GRID,
        mapOf(
            Option.Facet.X to x,
            Option.Facet.Y to y,
            Option.Facet.SCALES to scales,
            Option.Facet.X_ORDER to xOrder,
            Option.Facet.Y_ORDER to yOrder,
            Option.Facet.X_FORMAT to xFormat,
            Option.Facet.Y_FORMAT to yFormat,
        ).filterNonNullValues()
    )
}

