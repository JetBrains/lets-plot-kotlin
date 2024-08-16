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
 * @param alpha Transparency level. Can be a single value or a list to customize each key.
 * @param color Line color. Can be a single value or a list to customize each key.
 * @param fill Fill color. Can be a single value or a list to customize each key.
 * @param shape Shape of the point. Can be a single value or a list to customize each key.
 * @param size Size of the geometry. Can be a single value or a list to customize each key.
 * @param width Width. Can be a single value or a list to customize each key.
 * @param height Height. Can be a single value or a list to customize each key.
 * @param linetype Type of the line. Can be a single value or a list to customize each key.
 * @param stroke Width of the shape border. Can be a single value or a list to customize each key.
 */
fun guideLegend(
    title: String? = null,
    @Suppress("SpellCheckingInspection")
    nrow: Int? = null,
    @Suppress("SpellCheckingInspection")
    ncol: Int? = null,
    byRow: Boolean? = null,
    alpha: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    shape: Any? = null,
    size: Any? = null,
    width: Any? = null,
    height: Any? = null,
    linetype: Any? = null,
    stroke: Any? = null
): Map<String, Any> {
    val overrides = aesOverrides(alpha, color, fill, shape, size, width, height, linetype, stroke)

    return mapOf(
        Option.Meta.NAME to Option.Guide.LEGEND,
        Option.Guide.TITLE to title,
        Option.Guide.Legend.ROW_COUNT to nrow,
        Option.Guide.Legend.COL_COUNT to ncol,
        Option.Guide.Legend.BY_ROW to byRow,
        Option.Guide.Legend.OVERRIDE_AES to overrides
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
 * @param alpha Transparency level. Can be a single value or a list to customize each key.
 * @param color Line color. Can be a single value or a list to customize each key.
 * @param fill Fill color. Can be a single value or a list to customize each key.
 * @param shape Shape of the point. Can be a single value or a list to customize each key.
 * @param size Size of the geometry. Can be a single value or a list to customize each key.
 * @param width Width. Can be a single value or a list to customize each key.
 * @param height Height. Can be a single value or a list to customize each key.
 * @param linetype Type of the line. Can be a single value or a list to customize each key.
 * @param stroke Width of the shape border. Can be a single value or a list to customize each key.
 */
fun guideColorbar(
    title: String? = null,
    barWidth: Number? = null,
    barHeight: Number? = null,
    nbin: Int? = null,
    alpha: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    shape: Any? = null,
    size: Any? = null,
    width: Any? = null,
    height: Any? = null,
    linetype: Any? = null,
    stroke: Any? = null
): Map<String, Any> {
    val overrides = aesOverrides(alpha, color, fill, shape, size, width, height, linetype, stroke)
    return mapOf(
        Option.Meta.NAME to Option.Guide.COLOR_BAR,
        Option.Guide.TITLE to title,
        Option.Guide.ColorBar.WIDTH to barWidth,
        Option.Guide.ColorBar.HEIGHT to barHeight,
        Option.Guide.ColorBar.BIN_COUNT to nbin,
        Option.Guide.Legend.OVERRIDE_AES to overrides
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

    alpha?.let { options.put("alpha", it) }
    color?.let { options.put("color", it) }
    fill?.let { options.put("fill", it) }
    shape?.let { options.put("shape", it) }
    size?.let { options.put("size", it) }
    linetype?.let { options.put("linetype", it) }
    manual?.let { options.put(Option.Layer.DEFAULT_LEGEND_GROUP_NAME, it) }

    return OptionsMap(Option.Plot.GUIDES, options)
}

/**
 * Function to set guides for scales.
 *
 * @param guideOptions Name-guide pairs where name should be an aesthetic name or group name used in the `layerKey()` function
 *  and value can either be a string ("colorbar"/"legend" or "none" to hide the guide) or a call to a guide function
 *  (`guideColorbar()`/`guideLegend()`) specifying additional arguments,
 *  e.g. `guides("Group" to guideLegend(title = "Color Zone", ncol = 2)`.
 *
 */
fun guidesAlt(
    vararg guideOptions: Pair<String, Any>
): OptionsMap {
    return OptionsMap(Option.Plot.GUIDES, guideOptions.toMap())
}

// Guides with title (without name) is used by `labs()` function

internal fun guideTitleOption(title: String) = mapOf(Option.Guide.TITLE to title)


/**
 * Function to configure a custom legend.
 *
 * @param label Text for the element in the custom legend.
 * @param group Group name by which elements are combined into a legend group.
 * @param index Position of the element in the custom legend.
 * @param alpha Transparency level.
 * @param color Line color.
 * @param fill Fill color.
 * @param shape Shape of the point.
 * @param size Size of the geometry.
 * @param width Width.
 * @param height Height.
 * @param linetype Type of the line.
 * @param stroke Width of the shape border.
 *
 */
fun layerKey(
    label: String,
    group: String? = null,
    index: Int? = null,
    alpha: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    shape: Any? = null,
    size: Any? = null,
    width: Any? = null,
    height: Any? = null,
    linetype: Any? = null,
    stroke: Any? = null,
): Map<String, Any> {
    val options = HashMap<String, Any>()

    options += mapOf(
        Option.Layer.LayerKey.LABEL to label,
        Option.Layer.LayerKey.GROUP to group,
        Option.Layer.LayerKey.INDEX to index
    ).filterNonNullValues()

    aesOverrides(alpha, color, fill, shape, size, width, height, linetype, stroke)?.let { options += it }

    return options
}

/**
 * Function to set new aesthetic values to override the default legend appearance.
 *
 */
internal fun aesOverrides(
    alpha: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    shape: Any? = null,
    size: Any? = null,
    width: Any? = null,
    height: Any? = null,
    linetype: Any? = null,
    stroke: Any? = null,
): Map<String, Any>? {
    val options = HashMap<String, Any>()

    alpha?.let { options.put("alpha", it) }
    color?.let { options.put("color", it) }
    fill?.let { options.put("fill", it) }
    shape?.let { options.put("shape", it) }
    size?.let { options.put("size", it) }
    width?.let { options.put("width", it) }
    height?.let { options.put("height", it) }
    linetype?.let { options.put("linetype", it) }
    stroke?.let { options.put("stroke", it) }

    return if (options.isEmpty()) null else options
}