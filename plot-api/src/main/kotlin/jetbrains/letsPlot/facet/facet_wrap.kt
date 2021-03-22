/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.facet

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.filterNonNullValues

/**
 *
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `ncol`, `nrow` and `dir` settings.
 *
 * @param facets [string | list], one or more faceting variable names.
 * @param ncol Number of columns.
 * @param nrow Number of rows.
 * @param order [int | list], specifies ordering direction panels.
 *              1 - ascending, -1 - descending, None - default (ascending).
 *              The `order` values are positionally matched to variables in `facets`.
 * @param format [string | list], specifies the format pattern for displaying faceting values.
 *              The `format` values are positionally matched to variables in `facets`.
 * @param dir Direction: either "h" for horizontal, the default, or "v", for vertical.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "Score: {}" -> "Score: 12.454789"
 *
 */
@Suppress("FunctionName")
fun facet_wrap(
    facets: Any,
    ncol: Any? = null,
    nrow: Any? = null,
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
            Option.Facet.FACETS_ORDER to order,
            Option.Facet.FACETS_FORMAT to format,
            Option.Facet.FACETS_FILL_DIR to dir,
        ).filterNonNullValues()
    )
}

