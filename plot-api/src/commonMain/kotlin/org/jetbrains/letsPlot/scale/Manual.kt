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
 * Create your own discrete scale for color aesthetic.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
 *
 * @param values list of strings (encoding colors) or color values
 *      A set of aesthetic values to map data values to.
 *      Values will be matched with the limits of the scale (if specified).
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks)
 * @param limits
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format
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
fun scaleColorManual(
    values: List<Any>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for fill aesthetic.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
 *
 * @param values list of strings (encoding colors) or color values
 *      A set of aesthetic values to map data values to.
 *      Values will be matched with the limits of the scale (if specified).
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks)
 * @param limits
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
fun scaleFillManual(
    values: List<Any>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for size aesthetic
 *
 * @param values list of numbers encoding size.
 *      A set of aesthetic values to map data values to.
 *      Values will be matched with the limits of the scale (if specified).
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks)
 * @param limits
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format
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
fun scaleSizeManual(
    values: List<Number>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for shape aesthetic.
 *
 * ## Examples
 *
 * - [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
 *
 * @param values list of numbers encoding shape.
 *      A set of aesthetic values to map data values to.
 *      Values will be matched with the limits of the scale (if specified).
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks)
 * @param limits
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format
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
fun scaleShapeManual(
    values: List<Number>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for linetype aesthetic
 *
 * @param values list of numbers encoding linetype.
 *      A set of aesthetic values to map data values to.
 *      Values will be matched with the limits of the scale (if specified).
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks)
 * @param limits
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format
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
@Suppress("SpellCheckingInspection")
fun scaleLinetypeManual(
    values: List<Number>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)

/**
 * Create your own discrete scale for alpha aesthetic
 *
 * @param values list of alpha values.
 *      A set of aesthetic values to map data values to.
 *      Values will be matched with the limits of the scale (if specified).
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks)
 * @param limits
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param format
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
fun scaleAlphaManual(
    values: List<Double>,
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
            Option.Scale.OUTPUT_VALUES to values
        )
    )
)
