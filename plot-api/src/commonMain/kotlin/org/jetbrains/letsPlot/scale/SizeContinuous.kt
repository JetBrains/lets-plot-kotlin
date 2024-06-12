/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale

/**
 * Continuous scale for size (~radius).
 *
 * @param range The range of sizes that the input values are mapped to.
 * @param name  The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide A result returned by `guideLegend()` function or "none" to hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "log2", "symlog", "reverse", "sqrt").
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
 */
fun scaleSize(
    range: Pair<Number, Number>? = null,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Number? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.SIZE,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.RANGE to range?.toList()
        )
    )
)

/**
 * Continuous scale for size (~radius) that maps 0 value to 0 size.
 *
 * @param maxSize The upper limit of size that the input values are mapped to.
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide A result returned by `guideLegend()` function or "none" to hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "log2", "symlog", "reverse", "sqrt").
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
 */
fun scaleSizeArea(
    maxSize: Number? = null,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Number? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.SIZE,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.MAX_SIZE to maxSize,
            Option.Scale.SCALE_MAPPER_KIND to "size_area"
        )
    )
)
