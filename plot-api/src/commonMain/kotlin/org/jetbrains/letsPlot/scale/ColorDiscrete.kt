/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.plot.base.Aes


/**
 * Qualitative color scale for `fill` aesthetic.
 * Defaults to the Brewer 'Set1' palette.
 *
 * @param direction default = 1.
 *  Only for color scales: sets the order of colors in the scale.
 *  If -1, the order of colors is reversed.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits List of data values.
 *  A vector specifying values to display with the scale and their order in guides (axis).
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 * @param scaleMapperKind The type of the scale:
 *  ("color_gradient", "color_gradient2", "color_gradientn", "color_hue", "color_grey", "color_brewer", "color_cmap").
 *  If null (the default) and the scale is color, then "color_brewer" will be used.
 * @param otherOptions Additional parameters for the specified scale type.
 */
fun scaleFillDiscrete(
    direction: Int? = null,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    scaleMapperKind: String? = null,
    otherOptions: Map<String, Any?>? = null
) = scaleDiscrete(
    aesthetic = Aes.FILL,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    scaleMapperKind = scaleMapperKind,
    otherOptions = otherOptions
)

/**
 * Qualitative color scale for `color` aesthetic.
 * Defaults to the Brewer 'Set1' palette.
 *
 * ## Examples
 *
 * - [legend_and_axis.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/legend_and_axis.ipynb)
 *
 * @param direction default = 1.
 *  Only for color scales: sets the order of colors in the scale.
 *  If -1, the order of colors is reversed.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits List of data values.
 *  A vector specifying values to display with the scale and their order in guides (axis).
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 * @param scaleMapperKind The type of the scale:
 *  ("color_gradient", "color_gradient2", "color_gradientn", "color_hue", "color_grey", "color_brewer", "color_cmap").
 *  If null (the default) and the scale is color, then "color_brewer" will be used.
 * @param otherOptions Additional parameters for the specified scale type.
 */
fun scaleColorDiscrete(
    direction: Int? = null,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    scaleMapperKind: String? = null,
    otherOptions: Map<String, Any?>? = null
) = scaleDiscrete(
    aesthetic = Aes.COLOR,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    scaleMapperKind = scaleMapperKind,
    otherOptions = otherOptions
)
