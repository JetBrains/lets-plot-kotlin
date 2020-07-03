/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale

/**
 * Create your own discrete scale for color aesthetic
 *
 * @param values list of strings (encoding colors) or color values
 *      A set of aesthetic values to map data values to. If this is a named vector, then the values will be matched
 *      based on the names. If unnamed, values will be matched in order (usually alphabetical) with the limits of
 *      the scale.
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
 */
@Suppress("FunctionName")
fun scale_color_manual(
    values: List<Any>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for fill aesthetic
 *
 * @param values list of strings (encoding colors) or color values
 *      A set of aesthetic values to map data values to. If this is a named vector, then the values will be matched
 *      based on the names. If unnamed, values will be matched in order (usually alphabetical) with the limits of
 *      the scale.
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
 */
@Suppress("FunctionName")
fun scale_fill_manual(
    values: List<Any>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for size aesthetic
 *
 * @param values list of numbers encoding size.
 *      A set of aesthetic values to map data values to. If this is a named vector, then the values will be matched
 *      based on the names. If unnamed, values will be matched in order (usually alphabetical) with the limits of
 *      the scale.
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
 */
@Suppress("FunctionName")
fun scale_size_manual(
    values: List<Number>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for shape aesthetic
 *
 * @param values list of numbers encoding shape.
 *      A set of aesthetic values to map data values to. If this is a named vector, then the values will be matched
 *      based on the names. If unnamed, values will be matched in order (usually alphabetical) with the limits of
 *      the scale.
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
 */
@Suppress("FunctionName")
fun scale_shape_manual(
    values: List<Number>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for linetype aesthetic
 *
 * @param values list of numbers encoding linetype.
 *      A set of aesthetic values to map data values to. If this is a named vector, then the values will be matched
 *      based on the names. If unnamed, values will be matched in order (usually alphabetical) with the limits of
 *      the scale.
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
 */
@Suppress("FunctionName")
fun scale_linetype_manual(
    values: List<Number>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for alpha aesthetic
 *
 * @param values list of alpha values.
 *      A set of aesthetic values to map data values to. If this is a named vector, then the values will be matched
 *      based on the names. If unnamed, values will be matched in order (usually alphabetical) with the limits of
 *      the scale.
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
 */
@Suppress("FunctionName")
fun scale_alpha_manual(
    values: List<Double>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)
