/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Legend guide.
 * Legend type guide shows key (i.e., geoms) mapped onto values.
 *
 * @param title Title of guide.
 * @param nrow A number of rows in legend's guide.
 * @param ncol A number of columns in legend's guide.
 * @param byRow A type of output: by row (default), or by column.
 */
fun guideLegend(
    title: String? = null,
    @Suppress("SpellCheckingInspection")
    nrow: Int? = null,
    @Suppress("SpellCheckingInspection")
    ncol: Int? = null,
    byRow: Boolean? = null
): Map<String, Any> {
    return mapOf(
        Option.Meta.NAME to Option.Guide.LEGEND,
        Option.Guide.TITLE to title,
        Option.Guide.Legend.ROW_COUNT to nrow,
        Option.Guide.Legend.COL_COUNT to ncol,
        Option.Guide.Legend.BY_ROW to byRow
    ).filterNonNullValues()
}

/**
 * Continuous color bar guide.
 * Color bar guide shows continuous color scales mapped onto values.
 * Color bar is available with `scaleFill` and `scaleColor`.
 *
 * @param title Title of guide.
 * @param barWidth Color bar width.
 * @param barHeight Color bar height.
 * @param nbin Number of bins in color bar.
 */
fun guideColorbar(
    title: String? = null,
    barWidth: Number? = null,
    barHeight: Number? = null,
    nbin: Int? = null
): Map<String, Any> {
    return mapOf(
        Option.Meta.NAME to Option.Guide.COLOR_BAR,
        Option.Guide.TITLE to title,
        Option.Guide.ColorBar.WIDTH to barWidth,
        Option.Guide.ColorBar.HEIGHT to barHeight,
        Option.Guide.ColorBar.BIN_COUNT to nbin
    ).filterNonNullValues()
}

/**
 * Function to set guides for scales.
 * Sets the mapping between scale and guide.
 * The guide can either be a string ("colorbar"/"legend" or "none" to hide the guide)
 * or a call to a guide function (`guideColorbar()`/`guideLegend()`) specifying additional arguments.
 *
 * @param alpha String or guide function.
 *  The guide for alpha scale.
 * @param color String or guide function.
 *  The guide for color scale.
 * @param fill String or guide function.
 *  The guide for fill scale.
 * @param shape String or guide function.
 *  The guide for shape scale.
 * @param size String or guide function.
 *  The guide for size scale.
 * @param linetype String or guide function.
 *  The guide for linetype scale.
 */
@Suppress("SpellCheckingInspection")
fun guides(
    alpha: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    shape: Any? = null,
    size: Any? = null,
    linetype: Any? = null
): OptionsMap {
    val options = HashMap<String, Any>()

    alpha?.let { options.put("alpha", it) }
    color?.let { options.put("color", it) }
    fill?.let { options.put("fill", it) }
    shape?.let { options.put("shape", it) }
    size?.let { options.put("size", it) }
    linetype?.let { options.put("linetype", it) }

    return OptionsMap(Option.Plot.GUIDES, options)
}