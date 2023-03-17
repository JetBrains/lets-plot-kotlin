/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale
import org.jetbrains.letsPlot.intern.checkScaleExpand

/**
 * Discrete scale for x axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels A vector of labels (on ticks).
 * @param limits
 *      A vector specifying values to display on the axis and their order.
 *      Setting limits will remove data not included in the list.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0, additive = 0.2.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param reverse When True the scale reversed.
 * @param position String
 *  The position of the axis:
 *    "left", "right" or "both" for y-axis;
 *    "top", "bottom" or "both" for x-axis.
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
fun scaleXDiscrete(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    reverse: Boolean? = null,
    position: String? = null
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
        position = position,
        otherOptions = Options(
            mapOf(
                Option.Scale.DISCRETE_DOMAIN to true,
                Option.Guide.REVERSE to reverse
            )
        )
    )
}

/**
 * Discrete scale for y axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels A vector of labels (on ticks).
 * @param limits
 *      A vector specifying values to display on the axis and their order.
 *      Setting limits will remove data not included in the list.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0, additive = 0.2.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param reverse When True the scale reversed.
 * @param position String
 *  The position of the axis:
 *    "left", "right" or "both" for y-axis;
 *    "top", "bottom" or "both" for x-axis.
 *
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
fun scaleYDiscrete(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    reverse: Boolean? = null,
    position: String? = null
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
        position = position,
        otherOptions = Options(
            mapOf(
                Option.Scale.DISCRETE_DOMAIN to true,
                Option.Guide.REVERSE to reverse
            )
        )
    )
}

/**
 * Reversed discrete scale for x axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels A vector of labels (on ticks).
 * @param limits
 *      A vector specifying values to display on the axis and their order.
 *      Setting limits will remove data not included in the list.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0, additive = 0.2.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position String
 *  The position of the axis:
 *    "left", "right" or "both" for y-axis;
 *    "top", "bottom" or "both" for x-axis.
 *
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
fun scaleXDiscreteReversed(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
): Scale {
    return scaleXDiscrete(
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue,
        format = format,
        reverse = true,
        position = position
    )
}

/**
 * Reversed discrete scale for y axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks
 *      A vector specifying values to display as ticks on axis.
 * @param labels A vector of labels (on ticks).
 * @param limits
 *      A vector specifying values to display on the axis and their order.
 *      Setting limits will remove data not included in the list.
 * @param expand
 *      A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0, additive = 0.2.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position String
 *  The position of the axis:
 *    "left", "right" or "both" for y-axis;
 *    "top", "bottom" or "both" for x-axis.
 *
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
fun scaleYDiscreteReversed(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
): Scale {
    return scaleYDiscrete(
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue,
        format = format,
        reverse = true,
        position = position
    )
}
