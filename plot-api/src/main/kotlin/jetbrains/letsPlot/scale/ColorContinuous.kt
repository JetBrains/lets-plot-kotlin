/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.CHROMA
import jetbrains.datalore.plot.config.Option.Scale.DIRECTION
import jetbrains.datalore.plot.config.Option.Scale.END
import jetbrains.datalore.plot.config.Option.Scale.HIGH
import jetbrains.datalore.plot.config.Option.Scale.HUE_RANGE
import jetbrains.datalore.plot.config.Option.Scale.LOW
import jetbrains.datalore.plot.config.Option.Scale.LUMINANCE
import jetbrains.datalore.plot.config.Option.Scale.MID
import jetbrains.datalore.plot.config.Option.Scale.MIDPOINT
import jetbrains.datalore.plot.config.Option.Scale.SCALE_MAPPER_KIND
import jetbrains.datalore.plot.config.Option.Scale.START
import jetbrains.datalore.plot.config.Option.Scale.START_HUE
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale
import jetbrains.letsPlot.intern.checkGreyScaleStartEnd

/**
 * Defines smooth color gradient between two colors for fill aesthetic.
 *
 * @param low string
 *      Color for low end of gradient
 * @param high string
 *      Color for high end of gradient
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits list of numbers
 *      A numeric vector of length two providing limits of the scale.
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */

fun scaleFillContinuous(
    low: String? = null, high: String? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
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
            LOW to low,
            HIGH to high,
            SCALE_MAPPER_KIND to "color_gradient"
        )
    )
)

/**
 * Defines smooth color gradient between two colors for color aesthetic.
 *
 * @param low string
 *      Color for low end of gradient
 * @param high string
 *      Color for high end of gradient
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleColorContinuous(
    low: String? = null, high: String? = null,           // ToDo: string or Color
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
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
            LOW to low,
            HIGH to high,
            SCALE_MAPPER_KIND to "color_gradient"
        )
    )
)

/**
 * Defines smooth color gradient between two colors (low-high) for fill aesthetic.
 *
 * @param low string
 *      Color for low end of gradient
 * @param high string
 *      Color for high end of gradient
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleFillGradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleFillContinuous(
    low, high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    guide = guide,
    trans = trans
)

/**
 * Defines smooth color gradient between two colors (low-high) for color aesthetic.
 *
 * @param low string
 *      Color for low end of gradient
 * @param high string
 *      Color for high end of gradient
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleColorGradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorContinuous(
    low, high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    guide = guide,
    trans = trans
)

/**
 * Defines diverging color gradient (low-mid-high) for fill aesthetic.
 *
 * @param low string
 *      Color for low end of gradient
 * @param high string
 *      Color for high end of gradient
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleFillGradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
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
            LOW to low,
            HIGH to high,
            MID to mid,
            MIDPOINT to midpoint,
            SCALE_MAPPER_KIND to "color_gradient2"
        )
    )
)

/**
 * Defines diverging color gradient (low-mid-high) for color aesthetic.
 *
 * @param low string
 *      Color for low end of gradient
 * @param high string
 *      Color for high end of gradient
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleColorGradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
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
            LOW to low,
            HIGH to high,
            MID to mid,
            MIDPOINT to midpoint,
            SCALE_MAPPER_KIND to "color_gradient2"
        )
    )
)

/**
 * Sequential grey color scale for fill aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param start numeric
 *      Gray value at low end of palette in range `[0,1`]
 * @param end numeric
 *      Gray value at high end of palette in range `[0,1`]
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleFillGrey(
    start: Number? = null,
    end: Number? = null,
    direction: Int? = null,        // direction < 0 - reversed
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
): Scale {
    checkGreyScaleStartEnd(start, end)
    return Scale(
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
                START to start,
                END to end,
                DIRECTION to direction,
                SCALE_MAPPER_KIND to "color_grey"
            )
        )
    )
}

/**
 * Sequential grey color scale for color aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param start numeric
 *      Gray value at low end of palette in range `[0,1`]
 * @param end numeric
 *      Gray value at high end of palette in range `[0,1`]
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleColorGrey(
    start: Number? = null,
    end: Number? = null,
    direction: Int? = null,         // direction < 0 - reversed
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
): Scale {
    checkGreyScaleStartEnd(start, end)
    return Scale(
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
                START to start,
                END to end,
                DIRECTION to direction,
                SCALE_MAPPER_KIND to "color_grey"
            )
        )
    )
}

/**
 * Qualitative color scale with evenly spaced hues for fill aesthetic.
 *
 * @param h a pair of numbers
 *      Range of hues, in `[0,360`].
 * @param c numeric
 *      Chroma (intensity of color), maximum value varies depending on.
 * @param l numeric
 *      Luminance (lightness), in `[0,100`].
 * @param hstart list of two numbers
 *      Hue to start at.
 * @param direction numeric
 *      Direction to travel around the color wheel, 1 = clockwise (default), -1=counter-clockwise.
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleFillHue(
    h: Pair<Int, Int>? = null,
    c: Int? = null,
    l: Int? = null,
    @Suppress("SpellCheckingInspection")
    hstart: Int? = null,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
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
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to hstart,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_hue"
        )
    )
)

/**
 * Qualitative color scale with evenly spaced hues for color aesthetic.
 *
 * @param h a pair of numbers
 *      Range of hues, in `[0,360`].
 * @param c numeric
 *      Chroma (intensity of color), maximum value varies depending on.
 * @param l numeric
 *      Luminance (lightness), in `[0,100`].
 * @param hstart list of two numbers
 *      Hue to start at.
 * @param direction numeric
 *      Direction to travel around the color wheel, 1 = clockwise (default), -1=counter-clockwise.
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 * @param naValue
 *      Missing values will be replaced with this value.
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorBar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
fun scaleColorHue(
    h: Pair<Int, Int>? = null,
    c: Int? = null,
    l: Int? = null,
    @Suppress("SpellCheckingInspection")
    hstart: Int? = null,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
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
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to hstart,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_hue"
        )
    )
)
