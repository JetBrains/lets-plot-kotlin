/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Legend guide.
 * Legend type guide shows key (i.e., geoms) mapped onto values.
 *
 * @param nrow A number of rows in legend's guide
 * @param ncol A number of columns in legend's guide
 * @param byrow A type of output: by row (default), or by column
 */
@Suppress("FunctionName")
fun guide_legend(
    nrow: Int? = null,
    ncol: Int? = null,
    byrow: Boolean? = null
): Map<String, Any> {
    return mapOf(
        "nrow" to nrow,
        "ncol" to ncol,
        "byrow" to byrow
    ).filterNonNullValues()
}

/**
 * Continuous color bar guide.
 * Color bar guide shows continuous color scales mapped onto values.
 * Color bar is available with scale_fill and scale_color.
 * @param barwidth Color bar width
 * @param barheight Color bar height
 * @param nbin Number of bins in color bar
 */
@Suppress("FunctionName")
fun guide_colorbar(
    barwidth: Double? = null,
    barheight: Double? = null,
    nbin: Int? = null
): Map<String, Any> {
    return mapOf(
        "barwidth" to barwidth,
        "barheight" to barheight,
        "nbin" to nbin
    ).filterNonNullValues()
}
