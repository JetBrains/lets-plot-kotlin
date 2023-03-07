/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale

/**
 * Qualitative color scale with evenly spaced hues for the specified aesthetics.
 * Defaults to the Brewer 'Set2' palette (or 'Set3' if the categories count > 8).
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param direction Number, default = 1.
 *  Direction to travel around the color wheel, 1 = clockwise, -1 = counter-clockwise.
 * @param name String,
 *  the name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks List of data values.
 *  A vector specifying values to display as breaks (ticks) on guides (axis).
 * @param labels List of Strings.
 *  A vector of labels (on ticks).
 * @param limits List of data values.
 *  A vector specifying values to display with the scale and their order in guides (axis).
 * @param naValue Missing values will be replaced with this value.
 * @param format String,
 *  specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleDiscrete(
    aesthetic: Any,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = aesthetic,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.DIRECTION to direction,
            Option.Scale.DISCRETE_DOMAIN to true
        )
    )
)

/**
 * Qualitative color scale with evenly spaced hues for fill aesthetic.
 * Defaults to the Brewer 'Set2' palette (or 'Set3' if the categories count > 8)
 *
 * @param direction numeric
 *      Direction to travel around the color wheel, 1 = clockwise (default), -1=counter-clockwise
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of data values
 *      A vector specifying values to display as breaks (ticks) on guides (axis)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits list of data values
 *      A vector specifying values to display with the scale and their order in guides (axis).
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param format string
 *      Specifies the format pattern for labels on the scale.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorbar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
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
fun scaleFillDiscrete(
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = scaleDiscrete(
    aesthetic = Aes.FILL,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide
)

/**
 * Qualitative color scale with evenly spaced hues for color aesthetic.
 * Defaults to the Brewer 'Set2' palette (or 'Set3' if the categories count > 8)
 *
 * ## Examples
 *
 * - [legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/legend_and_axis.ipynb)
 *
 * @param direction numeric
 *      Direction to travel around the color wheel, 1 = clockwise (default), -1=counter-clockwise
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of data values
 *      A vector specifying values to display as breaks (ticks) on guides (axis)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits list of data values
 *      A vector specifying values to display with the scale and their order in guides (axis).
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param format string
 *      Specifies the format pattern for labels on the scale.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorbar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
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
fun scaleColorDiscrete(
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = scaleDiscrete(
    aesthetic = Aes.COLOR,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide
)
