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
 * Sequential, diverging and qualitative color scales from colorbrewer.org for the specified aesthetics.
 *
 * Palettes:
 *
 * - Diverging : BrBG, PiYG, PRGn, PuOr, RdBu, RdGy, RdYlBu, RdYlGn, Spectral.
 * - Qualitative : Accent, Dark2, Paired, Pastel1, Pastel2, Set1, Set2, Set3.
 * - Sequential : Blues, BuGn, BuPu, GnBu, Greens, Greys, Oranges, OrRd, PuBu, PuBuGn, PuRd, Purples, RdPu, Reds, YlGn, YlGnBu, YlOrBr, YlOrRd.
 *
 *
 * ## Examples
 *
 * - [scale_functions.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scale_functions.ipynb)
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param type One of seq (sequential), div (diverging) or qual (qualitative) types of scales.
 * @param palette String or Number.
 *  If a string, will use that named palette. If a number, will index into the list of palettes of appropriate type.
 * @param direction default = 1.
 *  Sets the order of colors in the scale. If 1 colors are as output by brewer.pal.
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
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleBrewer(
    aesthetic: Any,
    type: String? = null,
    palette: Any? = null,
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
            Option.Scale.PALETTE_TYPE to type,
            Option.Scale.PALETTE to palette,
            Option.Scale.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to Option.Scale.MapperKind.COLOR_BREWER
        )
    )
)

/**
 * Sequential, diverging and qualitative color scales from colorbrewer.org for `fill` aesthetic.
 *
 * Palettes:
 *
 * - Diverging : BrBG, PiYG, PRGn, PuOr, RdBu, RdGy, RdYlBu, RdYlGn, Spectral.
 * - Qualitative : Accent, Dark2, Paired, Pastel1, Pastel2, Set1, Set2, Set3.
 * - Sequential : Blues, BuGn, BuPu, GnBu, Greens, Greys, Oranges, OrRd, PuBu, PuBuGn, PuRd, Purples, RdPu, Reds, YlGn, YlGnBu, YlOrBr, YlOrRd.
 *
 * @param type One of seq (sequential), div (diverging) or qual (qualitative) types of scales.
 * @param palette String or Number.
 *  If a string, will use that named palette. If a number, will index into the list of palettes of appropriate type.
 * @param direction Sets the order of colors in the scale. If 1, the default, colors are as output by brewer.pal.
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
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleFillBrewer(
    type: String? = null,
    palette: Any? = null,
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
) = scaleBrewer(
    aesthetic = Aes.FILL,
    type = type,
    palette = palette,
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
 * Sequential, diverging and qualitative color scales from colorbrewer.org for `color` aesthetic.
 *
 * Palettes:
 *
 * - Diverging : BrBG, PiYG, PRGn, PuOr, RdBu, RdGy, RdYlBu, RdYlGn, Spectral.
 * - Qualitative : Accent, Dark2, Paired, Pastel1, Pastel2, Set1, Set2, Set3.
 * - Sequential : Blues, BuGn, BuPu, GnBu, Greens, Greys, Oranges, OrRd, PuBu, PuBuGn, PuRd, Purples, RdPu, Reds, YlGn, YlGnBu, YlOrBr, YlOrRd.
 *
 * @param type One of seq (sequential), div (diverging) or qual (qualitative) types of scales.
 * @param palette String or Number.
 *  If a string, will use that named palette. If a number, will index into the list of palettes of appropriate type.
 * @param direction Sets the order of colors in the scale. If 1, the default, colors are as output by brewer.pal.
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
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleColorBrewer(
    type: String? = null,
    palette: Any? = null,
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
) = scaleBrewer(
    aesthetic = Aes.COLOR,
    type = type,
    palette = palette,
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