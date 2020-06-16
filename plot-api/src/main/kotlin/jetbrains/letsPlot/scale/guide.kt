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
 * @param byRow A type of output: by row (default), or by column
 */
@Suppress("FunctionName")
fun guide_legend(
    @Suppress("SpellCheckingInspection")
    nrow: Int? = null,
    @Suppress("SpellCheckingInspection")
    ncol: Int? = null,
    byRow: Boolean? = null
): Map<String, Any> {
    @Suppress("SpellCheckingInspection")
    return mapOf(
        "nrow" to nrow,
        "ncol" to ncol,
        "byrow" to byRow
    ).filterNonNullValues()
}

/**
 * Continuous color bar guide.
 * Color bar guide shows continuous color scales mapped onto values.
 * Color bar is available with scale_fill and scale_color.
 * @param barWidth Color bar width
 * @param barHeight Color bar height
 * @param nbin Number of bins in color bar
 */
@Suppress("FunctionName", "SpellCheckingInspection")
fun guide_colorbar(
    barWidth: Double? = null,
    barHeight: Double? = null,
    nbin: Int? = null
): Map<String, Any> {
    return mapOf(
        "barwidth" to barWidth,
        "barheight" to barHeight,
        "nbin" to nbin
    ).filterNonNullValues()
}
