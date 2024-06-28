/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.plot.base.Aes
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
 * @param manual String or guide function.
 *  The guide for a custom legend.
 */
@Suppress("SpellCheckingInspection")
fun guides(
    alpha: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    shape: Any? = null,
    size: Any? = null,
    linetype: Any? = null,
    manual: Any? = null
): OptionsMap {
    val options = HashMap<String, Any>()

    fun addValue(keyGuide: String, value: Any?) {
        if (value != null) {
            require(value is Map<*,*> || value is String) {
                "Unknown guide value: $value. The guide value should be a String (e.g. name = \"none\") " +
                        "or a call to a guide function (`guideColorbar()`/`guideLegend()`)"
            }
            options[keyGuide] = value
        }
    }
    addValue("alpha", alpha)
    addValue("color", color)
    addValue("fill", fill)
    addValue("shape", shape)
    addValue("size", size)
    addValue("linetype", linetype)
    addValue(Option.Layer.DEFAULT_LEGEND_GROUP_NAME, manual)

    return OptionsMap(Option.Plot.GUIDES, options)
}

/**
 * Function to set guides for scales.
 * Name-guide pairs where name should be an aesthetic name or group name used in the `layerKey()` function.
 *
 */
fun guides(
    vararg guideOptions: Pair<String, Any>
): OptionsMap {
    return OptionsMap(Option.Plot.GUIDES, guideOptions.toMap())
}

// Guides with title (without name) is used by `labs()` function

private fun titleGuide(title: String) = mapOf(Option.Guide.TITLE to title)

internal fun titleGuides(
    x: String? = null,
    y: String? = null,
    alpha: String? = null,
    color: String? = null,
    fill: String? = null,
    shape: String? = null,
    size: String? = null,
    width: String? = null,
    height: String? = null,
    linetype: String? = null,
    manual: String? = null
): OptionsMap {
    val options = HashMap<String, Any>()

    fun addValue(keyGuide: String, value: String?) =
        value?.let { options.put(keyGuide, titleGuide(it)) }

    addValue("x", x)
    addValue("y", y)
    addValue("alpha", alpha)
    addValue("color", color)
    addValue("fill", fill)
    addValue("shape", shape)
    addValue("size", size)
    addValue("width", width)
    addValue("height", height)
    addValue("linetype", linetype)
    addValue(Option.Layer.DEFAULT_LEGEND_GROUP_NAME, manual)

    return OptionsMap(Option.Plot.GUIDES, options)
}

internal fun titleGuides(
    vararg titles: Pair<String, String>
): OptionsMap {
    val options = titles.associate { (key, title) -> key to titleGuide(title) }
    return OptionsMap(Option.Plot.GUIDES, options)
}