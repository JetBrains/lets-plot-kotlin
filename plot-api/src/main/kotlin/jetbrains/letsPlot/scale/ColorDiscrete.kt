/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale

/**
 * Qualitative color scale with evenly spaced hues for color aesthetic.
 *
 * @param h pair of two numbers
 *      Range of hues, from 0 to 360
 * @param c numeric in range (0,99)
 *      Chroma (intensity of color), 0 is grey, default - 50
 * @param l numeric in range (0,99)
 *      Luminance (lightness), 0 - black, 99 - white, default - 90
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
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 */
fun scale_fill_discrete(
    h: Pair<Int, Int>? = null,
    c: Int? = null,
    l: Int? = null,
    @Suppress("SpellCheckingInspection")
    hstart: Int? = null,
    direction: Int = 1,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.HUE_RANGE to h,
            Option.Scale.CHROMA to c,
            Option.Scale.LUMINANCE to l,
            Option.Scale.START_HUE to hstart,
            Option.Scale.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to "color_hue",
            Option.Scale.DISCRETE_DOMAIN to true
        )
    )
)

/**
 * Qualitative color scale with evenly spaced hues for fill aesthetic.
 *
 * @param h pair of two numbers
 *      Range of hues, from 0 to 360
 * @param c numeric in range (0,99)
 *      Chroma (intensity of color), 0 is grey, default - 50
 * @param l numeric in range (0,99)
 *      Luminance (lightness), 0 - black, 99 - white, default - 90
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
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 */
fun scale_color_discrete(
    h: Pair<Int, Int>? = null,
    c: Int? = null,
    l: Int? = null,
    @Suppress("SpellCheckingInspection")
    hstart: Int? = null,
    direction: Int = 1,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.HUE_RANGE to h,
            Option.Scale.CHROMA to c,
            Option.Scale.LUMINANCE to l,
            Option.Scale.START_HUE to hstart,
            Option.Scale.DIRECTION to direction,
            Option.Scale.SCALE_MAPPER_KIND to "color_hue",
            Option.Scale.DISCRETE_DOMAIN to true
        )
    )
)
