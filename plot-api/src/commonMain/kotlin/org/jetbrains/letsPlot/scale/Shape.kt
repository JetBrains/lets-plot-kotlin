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
 * Scale for shapes
 *
 * @param solid boolean
 *      Are the shapes solid (default) True, or hollow (False)?
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels : list of strings
 *      A vector of labels (on ticks)
 * @param limits list
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format string
 *      Specifies the format pattern for labels on the scale.
 * @param guide A result returned by guideLegend() function or "none" to hide the guide.
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
fun scaleShape(
    solid: Boolean? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.SHAPE,
    name = name,
    breaks = breaks,
    labels = labels,
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