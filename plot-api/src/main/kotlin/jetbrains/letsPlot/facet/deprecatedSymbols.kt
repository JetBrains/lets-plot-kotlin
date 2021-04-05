/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName", "SpellCheckingInspection")

package jetbrains.letsPlot.facet

@Deprecated("", ReplaceWith("facetGrid(x, y, xOrder, yOrder, xFormat, yFormat)"))
fun facet_grid(
    x: String? = null,
    y: String? = null,
    xOrder: Int = 1,
    yOrder: Int = 1,
    xFormat: String? = null,
    yFormat: String? = null
) = facetGrid(x, y, xOrder, yOrder, xFormat, yFormat)


@Deprecated("", ReplaceWith("facetWrap(facets, ncol, nrow, order, format, dir)"))
fun facet_wrap(
    facets: Any,
    ncol: Any? = null,
    nrow: Any? = null,
    order: Any? = null,
    format: Any? = null,
    dir: String = "h"
) = facetWrap(facets, ncol, nrow, order, format, dir)