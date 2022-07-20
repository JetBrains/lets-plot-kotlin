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
 * Use this scale when your data has already been scaled.
 * I.e. it already represents aesthetic values that ggplot2 can handle directly.
 * This will not produce a legend unless you also supply the breaks and labels.
 *
 * Input data expected: list of strings containing
 *   a) names of colors (i.e. 'green')
 *   b) hex codes of colors (i.e 'x00ff00')
 *   c) css colors (i.e 'rgb(0,255,0)')
 *
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorbar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
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
fun scaleColorIdentity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SCALE_MAPPER_KIND to "identity"
        )
    )
)

/**
 * Use this scale when your data has already been scaled.
 * I.e. it already represents aesthetic values that ggplot2 can handle directly.
 * This will not produce a legend unless you also supply the breaks and labels.
 *
 * Input data expected: list of strings containing
 *   a) names of colors (i.e. 'green')
 *   b) hex codes of colors (i.e 'x00ff00')
 *   c) css colors (i.e 'rgb(0,255,0)')
 *
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guideColorbar(), guideLegend())
 *      specifying additional arguments.
 *      "none" will hide the guide.
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
fun scaleFillIdentity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SCALE_MAPPER_KIND to "identity"
        )
    )
)

/**
 * Use this scale when your data has already been scaled.
 * I.e. it already represents aesthetic values that ggplot2 can handle directly.
 * This will not produce a legend unless you also supply the breaks and labels.
 *
 * Input data expected: numeric codes of shapes.
 *
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
fun scaleShapeIdentity(
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
            Option.Scale.SCALE_MAPPER_KIND to "identity",
            Option.Scale.DISCRETE_DOMAIN to true
        )
    )
)

/**
 * Use this scale when your data has already been scaled.
 * I.e. it already represents aesthetic values that ggplot2 can handle directly.
 * This will not produce a legend unless you also supply the breaks and labels.
 *
 * Input data expected: numetic codes or names of line types (i.e 'dotdash').
 * The codes are: 0 = blank, 1 = solid, 2 = dashed, 3 = dotted, 4 = dotdash, 5 = longdash, 6 = twodash
 *
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
@Suppress("SpellCheckingInspection")
fun scaleLinetypeIdentity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.LINETYPE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SCALE_MAPPER_KIND to "identity",
            Option.Scale.DISCRETE_DOMAIN to true
        )
    )
)

/**
 * Use this scale when your data has already been scaled.
 * I.e. it already represents aesthetic values that ggplot2 can handle directly.
 * This will not produce a legend unless you also supply the breaks and labels.
 *
 * Input data expected: numetic values in range `[0..1`]
 *
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
fun scaleAlphaIdentity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.ALPHA,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SCALE_MAPPER_KIND to "identity"
        )
    )
)

/**
 * Use this scale when your data has already been scaled.
 * I.e. it already represents aesthetic values that ggplot2 can handle directly.
 * This will not produce a legend unless you also supply the breaks and labels.
 *
 * Input data expected: positive numeric values
 *
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
fun scaleSizeIdentity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    format: String? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.SIZE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SCALE_MAPPER_KIND to "identity"
        )
    )
)