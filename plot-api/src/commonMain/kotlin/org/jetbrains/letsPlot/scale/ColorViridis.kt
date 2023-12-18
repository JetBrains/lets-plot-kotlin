/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale

/**
 *
 * The `viridis` color maps are designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * The `viridis` color scales are suitable for viewers with common forms of colour blindness.
 * See also [https://bids.github.io/colormap](https://bids.github.io/colormap).
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param option Name of colormap.
 *  Supported colormaps:
 *  - "magma" (or "A"),
 *  - "inferno" (or "B"),
 *  - "plasma" (or "C"),
 *  - "viridis" (or "D"),
 *  - "cividis" (or "E"),
 *  - "turbo",
 *  - "twilight".
 * @param alpha Alpha transparency channel. 0 means transparent and 1 means opaque.
 * @param begin default = 0.0.
 *  Corresponds to a color hue to start at. Accepts values between 0 and 1. Should be less than `end`.
 * @param end default = 1.0.
 *  Corresponds to a color hue to end with. Accepts values between 0 and 1. Should be greater than `begin`.
 * @param direction default = 1.
 *  Sets the order of colors in the scale.
 *  If -1, the order of colors is reversed.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits Data range for this scale.
 *  Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *  Discrete scale: list of data values to display, and their order.
 * @param naValue An aesthetic value which is used when data in not available.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "log2", "symlog", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleViridis(
    aesthetic: Any,
    option: String? = null,
    alpha: Number? = null,
    begin: Number? = null,
    end: Number? = null,
    direction: Number? = null,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = aesthetic,
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
            Option.Scale.Viridis.CMAP_NAME to option,
            Option.Scale.Viridis.ALPHA to alpha,
            Option.Scale.Viridis.BEGIN to begin,
            Option.Scale.Viridis.END to end,
            Option.Scale.Viridis.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to Option.Scale.MapperKind.COLOR_CMAP
        )
    )
)

/**
 *
 * The `viridis` color maps are designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * The `viridis` color scales are suitable for viewers with common forms of colour blindness.
 * See also [https://bids.github.io/colormap](https://bids.github.io/colormap).
 *
 * ## Examples
 *
 * - [color_space_update.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.4/color_space_update.ipynb)
 *
 * @param option Name of colormap.
 *  Supported colormaps:
 *  - "magma" (or "A"),
 *  - "inferno" (or "B"),
 *  - "plasma" (or "C"),
 *  - "viridis" (or "D"),
 *  - "cividis" (or "E"),
 *  - "turbo",
 *  - "twilight".
 * @param alpha Alpha transparency channel. 0 means transparent and 1 means opaque.
 * @param begin default = 0.0.
 *  Corresponds to a color hue to start at. Accepts values between 0 and 1. Should be less than `end`.
 * @param end default = 1.0.
 *  Corresponds to a color hue to end with. Accepts values between 0 and 1. Should be greater than `begin`.
 * @param direction default = 1.
 *  Sets the order of colors in the scale.
 *  If -1, the order of colors is reversed.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits Data range for this scale.
 *  Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *  Discrete scale: list of data values to display, and their order.
 * @param naValue An aesthetic value which is used when data in not available.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "log2", "symlog", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleFillViridis(
    option: String? = null,
    alpha: Number? = null,
    begin: Number? = null,
    end: Number? = null,
    direction: Number? = null,

    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleViridis(
    aesthetic = Aes.FILL,
    option = option,
    alpha = alpha,
    begin = begin, end = end,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 *
 * The `viridis` color maps are designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * The `viridis` color scales are suitable for viewers with common forms of colour blindness.
 * See also [https://bids.github.io/colormap](https://bids.github.io/colormap).
 *
 * ## Examples
 *
 * - [color_space_update.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.4/color_space_update.ipynb)
 *
 * @param option Name of colormap.
 *  Supported colormaps:
 *  - "magma" (or "A"),
 *  - "inferno" (or "B")
 *  - "plasma" (or "C")
 *  - "viridis" (or "D")
 *  - "cividis" (or "E")
 *  - "turbo"
 *  - "twilight"
 * @param alpha Alpha transparency channel (0 means transparent and 1 means opaque).
 * @param begin default = 0.0.
 *  Corresponds to a color hue to start at. Accepts values between 0 and 1. Should be less than `end`.
 * @param end default = 1.0.
 *  Corresponds to a color hue to end with. Accepts values between 0 and 1. Should be greater than `begin`.
 * @param direction default = 1.
 *  Sets the order of colors in the scale.
 *  If -1, the order of colors is reversed.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits Data range for this scale.
 *  Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *  Discrete scale: list of data values to display, and their order.
 * @param naValue An aesthetic value which is used when data in not available.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "log2", "symlog", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleColorViridis(
    option: String? = null,
    alpha: Number? = null,
    begin: Number? = null,
    end: Number? = null,
    direction: Number? = null,

    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleViridis(
    aesthetic = Aes.COLOR,
    option = option,
    alpha = alpha,
    begin = begin, end = end,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    lablim = lablim,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)