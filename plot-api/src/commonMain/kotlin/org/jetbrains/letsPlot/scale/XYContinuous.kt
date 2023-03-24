/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import org.jetbrains.letsPlot.intern.Scale
import org.jetbrains.letsPlot.intern.checkScaleExpand

/**
 * Continuous scale for x-axis.
 *
 * ## Examples
 *
 * - [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
 *
 * - [axis_position.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_position.ipynb)
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleXContinuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    trans: String? = null,
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
        trans = trans,
        position = position
    )
}

/**
 * Continuous scale for y-axis.
 *
 * ## Examples
 *
 * - [formatting_axes_etc.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
 *
 * - [axis_position.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_position.ipynb)
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleYContinuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    trans: String? = null,
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
        trans = trans,
        position = position
    )
}

/**
 * Continuous position scale (x) where trans="log10".
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleXLog10(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
) = scaleXContinuous(
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    format = format,
    trans = "log10",
    position = position
)

/**
 * Continuous position scale (y) where trans="log10".
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleYLog10(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
) = scaleYContinuous(
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    format = format,
    trans = "log10",
    position = position
)

/**
 * Continuous position scales (x) where trans="reverse".
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleXReverse(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
) = scaleXContinuous(
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    format = format,
    trans = "reverse",
    position = position
)

/**
 * Continuous position scales (y) where trans="reverse".
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleYReverse(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
) = scaleYContinuous(
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    format = format,
    trans = "reverse",
    position = position
)

/**
 * Continuous position scales (x) where trans="sqrt".
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleXSqrt(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
) = scaleXContinuous(
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    format = format,
    trans = "sqrt",
    position = position
)

/**
 * Continuous position scales (y) where trans="sqrt".
 *
 * @param name The name of the scale - used as the axis label or the legend title.
 *  If null, the default, the name of the scale is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 *  Set limits if you want values to be consistent across multiple plots.
 *  Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *  The vector size == 1 => only multiplicative expand (and additive expand by default).
 *  Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param position The position of the axis:
 *  - "left", "right" or "both" for y-axis;
 *  - "top", "bottom" or "both" for x-axis.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleYSqrt(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    position: String? = null
) = scaleYContinuous(
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    format = format,
    trans = "sqrt",
    position = position
)