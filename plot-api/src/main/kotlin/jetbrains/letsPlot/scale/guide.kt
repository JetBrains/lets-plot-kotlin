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
 * @param nRow A number of rows in legend's guide
 * @param nCol A number of columns in legend's guide
 * @param byRow A type of output: by row (default), or by column
 */
@Suppress("FunctionName")
fun guide_legend(
    nRow: Int? = null,
    nCol: Int? = null,
    byRow: Boolean? = null
): Map<String, Any> {
    @Suppress("SpellCheckingInspection")
    return mapOf(
        "nrow" to nRow,
        "ncol" to nCol,
        "byrow" to byRow
    ).filterNonNullValues()
}

/**
 * Continuous color bar guide.
 * Color bar guide shows continuous color scales mapped onto values.
 * Color bar is available with scale_fill and scale_color.
 * @param barWidth Color bar width
 * @param barHeight Color bar height
 * @param nBin Number of bins in color bar
 */
@Suppress("FunctionName", "SpellCheckingInspection")
fun guide_colorbar(
    barWidth: Double? = null,
    barHeight: Double? = null,
    nBin: Int? = null
): Map<String, Any> {
    return mapOf(
        "barwidth" to barWidth,
        "barheight" to barHeight,
        "nbin" to nBin
    ).filterNonNullValues()
}
