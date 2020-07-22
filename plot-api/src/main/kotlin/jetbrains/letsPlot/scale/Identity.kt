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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guide_colorbar(), guide_legend())
 *      specifying additional arguments; "none" will hide the guide.
 */
@Suppress("FunctionName")
fun scale_color_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guide_colorbar(), guide_legend())
 *      specifying additional arguments; "none" will hide the guide.
 */
@Suppress("FunctionName")
fun scale_fill_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guide_colorbar(), guide_legend())
 *      specifying additional arguments; "none" will hide the guide.
 */
@Suppress("FunctionName")
fun scale_shape_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.SHAPE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guide_colorbar(), guide_legend())
 *      specifying additional arguments; "none" will hide the guide.
 */
@Suppress("FunctionName")
fun scale_linetype_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.LINETYPE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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
 * Input data expected: numetic values in range [0..1]
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guide_colorbar(), guide_legend())
 *      specifying additional arguments; "none" will hide the guide.
 */
@Suppress("FunctionName")
fun scale_alpha_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.ALPHA,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
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
 * @param guide
 *      Guide to use for this scale.
 *      It can either be a string ("colorbar", "legend") or a call to a guide function (guide_colorbar(), guide_legend())
 *      specifying additional arguments; "none" will hide the guide.
 */
@Suppress("FunctionName")
fun scale_size_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.SIZE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SCALE_MAPPER_KIND to "identity"
        )
    )
)