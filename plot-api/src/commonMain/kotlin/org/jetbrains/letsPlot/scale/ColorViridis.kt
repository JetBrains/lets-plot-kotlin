/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale

/**
 *
 * The `viridis` color maps are designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * The `viridis` color scales are suitable for viewers with common forms of colour blindness.
 * See also https://bids.github.io/colormap/.
 *
 * @param option
 *      Name of colormap.
 *      Supported colormaps:
 *        - "magma" (or "A"),
 *        - "inferno" (or "B")
 *        - "plasma" (or "C")
 *        - "viridis" (or "D")
 *        - "cividis" (or "E")
 *        - "turbo"
 *        - "twilight"
 * @param alpha
 *      Alpha transparency channel. (0 means transparent and 1 means opaque).
 * @param begin default=0.0
 *      Corresponds to a color hue to start at. Accepts values between 0 and 1. Should be less than `end`.
 * @param end default=1.0
 *      Corresponds to a color hue to end with. Accepts values between 0 and 1. Should be greater than `begin`.
 * @param direction default=1
 *      Sets the order of colors in the scale.
 *      If -1, the order of colors is reversed.
 *
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of data values.
 *      A vector specifying values to display as ticks on axis.
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
fun scaleFillViridis(
    option: String? = null,
    alpha: Number? = null,
    begin: Number? = null,
    end: Number? = null,
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
            Option.Scale.Viridis.CMAP_NAME to option,
            Option.Scale.Viridis.ALPHA to alpha,
            Option.Scale.Viridis.BEGIN to begin,
            Option.Scale.Viridis.END to end,
            Option.Scale.Viridis.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to "color_cmap"  // ToDo: use Options constant (when available).
        )
    )
)

/**
 *
 * The `viridis` color maps are designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * The `viridis` color scales are suitable for viewers with common forms of colour blindness.
 * See also https://bids.github.io/colormap/.
 *
 * @param option
 *      Name of colormap.
 *      Supported colormaps:
 *        - "magma" (or "A"),
 *        - "inferno" (or "B")
 *        - "plasma" (or "C")
 *        - "viridis" (or "D")
 *        - "cividis" (or "E")
 *        - "turbo"
 *        - "twilight"
 * @param alpha
 *      Alpha transparency channel. (0 means transparent and 1 means opaque).
 * @param begin default=0.0
 *      Corresponds to a color hue to start at. Accepts values between 0 and 1. Should be less than `end`.
 * @param end default=1.0
 *      Corresponds to a color hue to end with. Accepts values between 0 and 1. Should be greater than `begin`.
 * @param direction default=1
 *      Sets the order of colors in the scale.
 *      If -1, the order of colors is reversed.
 *
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of data values.
 *      A vector specifying values to display as ticks on axis.
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
fun scaleColorViridis(
    option: String? = null,
    alpha: Number? = null,
    begin: Number? = null,
    end: Number? = null,
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
            Option.Scale.Viridis.CMAP_NAME to option,
            Option.Scale.Viridis.ALPHA to alpha,
            Option.Scale.Viridis.BEGIN to begin,
            Option.Scale.Viridis.END to end,
            Option.Scale.Viridis.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to "color_cmap"  // ToDo: use Options constant (when available).
        )
    )
)