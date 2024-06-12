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
 * Scale for shapes.
 *
 * @param solid Are the shapes solid (default) true, or hollow (false)?
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits Continuous scale: a numeric vector of length two providing limits of the scale.
 *  Discrete scale: a vector specifying the data range for the scale and the default order of their display in guides.
 * @param naValue An aesthetic value which is used when data in not available.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide A result returned by `guideLegend()` function or "none" to hide the guide.
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
fun scaleShape(
    solid: Boolean? = null,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.SHAPE,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SHAPE_SOLID to solid
        )
    )
)