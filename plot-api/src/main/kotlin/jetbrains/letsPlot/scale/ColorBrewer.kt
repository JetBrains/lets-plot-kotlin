/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale

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
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
@Suppress("FunctionName")
fun scale_fill_brewer(
    type: String? = null,
    palette: Any? = null,
    direction: Number? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
@Suppress("FunctionName")
fun scale_color_brewer(
    type: String? = null,
    palette: Any? = null,
    direction: Number? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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