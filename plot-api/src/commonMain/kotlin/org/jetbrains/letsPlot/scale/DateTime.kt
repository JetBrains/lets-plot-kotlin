/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.DATE_TIME
import jetbrains.datalore.plot.config.Option.Scale.TIME
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale
import org.jetbrains.letsPlot.intern.checkScaleExpand

/**
 * Position scale x for date/time data.
 *
 * ## Examples
 *
 * - [formatting_axes_etc.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
 *
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks).
 * @param limits data range for this scale.
 *      A pair of values providing limits of the scale. Use `null` to refer to default min/max.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue
 *       Missing values will be replaced with this value.
 * @param format
 *      Defines the format for labels on the scale. Also applies to `breaks`.
 *
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 *
 * Examples:
 * "%d.%m.%y" -> "06.08.19"
 * "%B %Y" -> "August 2019"
 * "%a, %e %b %Y %H:%M:%S" -> "Tue, 6 Aug 2019 04:46:35"
 *
 */
fun scaleXDateTime(
    name: String? = null,
    breaks: List<Any>? = null,      // ToDo: should understand Date
    labels: List<String>? = null,
    limits: Pair<Any?, Any?>? = null,
    expand: List<Number>? = null,
    naValue: Any? = null,
    format: String? = null,
): Scale {
    checkScaleExpand(expand)
    return Scale(
        aesthetic = Aes.X,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue,
        format = format,
        otherOptions = Options(
            mapOf(
                DATE_TIME to true
            )
        )
    )
}

/**
 * Position scale y for date/time data.
 *
 * ## Examples
 *
 * - [formatting_axes_etc.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
 *
 * @param name
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels
 *      A vector of labels (on ticks).
 * @param limits data range for this scale.
 *      A pair of values providing limits of the scale. Use `null` to refer to default min/max.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue
 *       Missing values will be replaced with this value.
 * @param format
 *      Defines the format for labels on the scale. Also applies to `breaks`.
 *
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 *
 * Examples:
 * "%d.%m.%y" -> "06.08.19"
 * "%B %Y" -> "August 2019"
 * "%a, %e %b %Y %H:%M:%S" -> "Tue, 6 Aug 2019 04:46:35"
 *
 */
fun scaleYDateTime(
    name: String? = null,
    breaks: List<Any>? = null,      // ToDo: should understand Date
    labels: List<String>? = null,
    limits: Pair<Any?, Any?>? = null,
    expand: List<Number>? = null,
    naValue: Any? = null,
    format: String? = null,
): Scale {
    checkScaleExpand(expand)
    return Scale(
        aesthetic = Aes.Y,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue,
        format = format,
        otherOptions = Options(
            mapOf(
                DATE_TIME to true
            )
        )
    )
}

/**
 * Position scale x for data representing "time delta" values expressed in milliseconds.
 *
 * ## Examples
 *
 * - [scale_time.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scale_time.ipynb)
 *
 * @param name
 *      The name of the scale - used as the axis label or the legend title.
 *      If None, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks
 *     A numeric vector of positions (of ticks).
 * @param labels
 *      A vector of labels (on ticks).
 * @param limits
 *      A numeric vector of length two providing limits of the scale.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue
 *       Missing values will be replaced with this value.
 *
 */
fun scaleXTime(
    name: String? = null,
    breaks: List<Any>? = null,       // ToDo: List<Number>?
    labels: List<String>? = null,
    limits: Pair<Any?, Any?>? = null,
    expand: List<Number>? = null,
    naValue: Any? = null,
): Scale {
    checkScaleExpand(expand)
    return Scale(
        aesthetic = Aes.X,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue,
        otherOptions = Options(
            mapOf(
                TIME to true
            )
        )
    )
}

/**
 * Position scale y for data representing "time delta" values expressed in milliseconds.
 *
 * ## Examples
 *
 * - [scale_time.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scale_time.ipynb)
 *
 * @param name
 *      The name of the scale - used as the axis label or the legend title.
 *      If None, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks
 *     A numeric vector of positions (of ticks).
 * @param labels
 *      A vector of labels (on ticks).
 * @param limits
 *      A numeric vector of length two providing limits of the scale.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue
 *       Missing values will be replaced with this value.
 *
 */
fun scaleYTime(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Any?, Any?>? = null,
    expand: List<Number>? = null,
    naValue: Any? = null,
): Scale {
    checkScaleExpand(expand)
    return Scale(
        aesthetic = Aes.Y,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue,
        otherOptions = Options(
            mapOf(
                TIME to true
            )
        )
    )
}