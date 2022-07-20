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
 * Sequential, diverging and qualitative color scales from colorbrewer.org for fill aesthetic. Color schemes provided
 * are particularly suited to display discrete values (levels of factors) on a map.
 *
 * @param type string
 *      One of seq (sequential), div (diverging) or qual (qualitative) types of scales.
 * @param palette string or number
 *      If a string, will use that named palette. If a number, will index into the list of palettes of appropriate type.
 * @param direction numeric
 *      Sets the order of colors in the scale. If 1, the default, colors are as output by brewer.pal.
 *      If -1, the order of colors is reversed.
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits data range for this scale.
 *      Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *      Discrete scale: list of data values to display, and their order.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format string
 *      Specifies the format pattern for labels on the scale.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorbar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
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
fun scaleFillBrewer(
    type: String? = null,
    palette: Any? = null,
    direction: Number? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.PALETTE_TYPE to type,
            Option.Scale.PALETTE to palette,
            Option.Scale.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to "color_brewer"
        )
    )
)

/**
 * Sequential, diverging and qualitative color scales from colorbrewer.org for color aesthetic. Color schemes provided
 * are particularly suited to display discrete values (levels of factors) on a map.
 *
 * @param type string
 *      One of seq (sequential), div (diverging) or qual (qualitative) types of scales.
 * @param palette string or number
 *      If a string, will use that named palette. If a number, will index into the list of palettes of appropriate type.
 * @param direction numeric
 *      Sets the order of colors in the scale. If 1, the default, colors are as output by brewer.pal.
 *      If -1, the order of colors is reversed.
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits data range for this scale.
 *      Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *      Discrete scale: list of data values to display, and their order.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format string
 *      Specifies the format pattern for labels on the scale.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorbar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
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
fun scaleColorBrewer(
    type: String? = null,
    palette: Any? = null,
    direction: Number? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.PALETTE_TYPE to type,
            Option.Scale.PALETTE to palette,
            Option.Scale.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to "color_brewer"
        )
    )
)