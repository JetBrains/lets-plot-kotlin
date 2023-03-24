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
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `ncol`, `nrow` and `dir` settings.
 *
 * ## Examples
 *
 * - [facets.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets.ipynb)
 *
 * - [facets_free_scales.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets_free_scales.ipynb)
 *
 * @param facets One or more faceting variable names.
 * @param ncol Number of columns.
 * @param nrow Number of rows.
 * @param scales Specifies whether scales are shared across all facets.
 *  default = "fixed" (shared) , "free" - vary across both rows and columns,
 *  "free_x" or "free_y" - vary across rows or columns respectively.
 * @param order Specifies ordering direction panels.
 *  1 - ascending, -1 - descending, null - ascending.
 *  The `order` values are positionally matched to variables in `facets`.
 * @param format Specifies the format pattern for displaying faceting values.
 *  The `format` values are positionally matched to variables in `facets`.
 * @param dir Direction: either "h" for horizontal, the default, or "v", for vertical.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun facetWrap(
    facets: Any,
    ncol: Any? = null,
    nrow: Any? = null,
    scales: String? = null,
    order: Any? = null,
    format: Any? = null,
    dir: String = "h"
): OptionsMap {
    return OptionsMap(
        Option.Plot.FACET,
        Option.Facet.NAME_WRAP,
        mapOf(
            Option.Facet.FACETS to facets,
            Option.Facet.NCOL to ncol,
            Option.Facet.NROW to nrow,
            Option.Facet.SCALES to scales,
            Option.Facet.FACETS_ORDER to order,
            Option.Facet.FACETS_FORMAT to format,
            Option.Facet.FACETS_FILL_DIR to dir,
        ).filterNonNullValues()
    )
}

