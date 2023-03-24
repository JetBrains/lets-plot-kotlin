/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.CHROMA
import jetbrains.datalore.plot.config.Option.Scale.COLORS
import jetbrains.datalore.plot.config.Option.Scale.DIRECTION
import jetbrains.datalore.plot.config.Option.Scale.END
import jetbrains.datalore.plot.config.Option.Scale.HIGH
import jetbrains.datalore.plot.config.Option.Scale.HUE_RANGE
import jetbrains.datalore.plot.config.Option.Scale.LOW
import jetbrains.datalore.plot.config.Option.Scale.LUMINANCE
import jetbrains.datalore.plot.config.Option.Scale.MID
import jetbrains.datalore.plot.config.Option.Scale.MIDPOINT
import jetbrains.datalore.plot.config.Option.Scale.MapperKind.COLOR_GRADIENT
import jetbrains.datalore.plot.config.Option.Scale.MapperKind.COLOR_GRADIENT2
import jetbrains.datalore.plot.config.Option.Scale.MapperKind.COLOR_GRADIENTN
import jetbrains.datalore.plot.config.Option.Scale.MapperKind.COLOR_GREY
import jetbrains.datalore.plot.config.Option.Scale.MapperKind.COLOR_HUE
import jetbrains.datalore.plot.config.Option.Scale.SCALE_MAPPER_KIND
import jetbrains.datalore.plot.config.Option.Scale.START
import jetbrains.datalore.plot.config.Option.Scale.START_HUE
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale
import org.jetbrains.letsPlot.intern.checkGreyScaleStartEnd


/**
 * Default color scale for `fill` aesthetic and continuous data.
 *
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A numeric vector of length two providing limits of the scale.
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
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
fun scaleFillContinuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleContinuous(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Default color scale for `color` aesthetic and continuous data.
 *
 * ## Examples
 *
 * - [legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/legend_and_axis.ipynb)
 *
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: (formats.md)[https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md]
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
fun scaleColorContinuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleContinuous(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Defines smooth color gradient between two colors for the specified aesthetics.
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param low Color for low end of gradient.
 * @param high Color for high end of gradient.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleGradient(
    aesthetic: Any,
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = aesthetic,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            LOW to low,
            HIGH to high,
            SCALE_MAPPER_KIND to COLOR_GRADIENT
        )
    )
)

/**
 * Defines smooth color gradient between two colors (low-high) for `fill` aesthetic.
 *
 * @param low Color for low end of gradient.
 * @param high Color for high end of gradient.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
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
fun scaleFillGradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGradient(
    aesthetic = Aes.FILL,
    low = low, high = high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Defines smooth color gradient between two colors (low-high) for `color` aesthetic.
 *
 * ## Examples
 *
 * - [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)
 *
 * - [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)
 *
 * @param low Color for low end of gradient.
 * @param high Color for high end of gradient.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleColorGradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGradient(
    aesthetic = Aes.COLOR,
    low = low, high = high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Defines diverging color gradient (low-mid-high) for the specified aesthetics.
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param low Color for low end of gradient.
 * @param high Color for high end of gradient.
 * @param midpoint default = 0.0.
 *  The midpoint (in data value) of the diverging scale.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleGradient2(
    aesthetic: Any,
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = aesthetic,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            LOW to low,
            HIGH to high,
            MID to mid,
            MIDPOINT to midpoint,
            SCALE_MAPPER_KIND to COLOR_GRADIENT2
        )
    )
)

/**
 * Defines diverging color gradient (low-mid-high) for `fill` aesthetic.
 *
 * @param low Color for low end of gradient.
 * @param high Color for high end of gradient.
 * @param midpoint default = 0.0.
 *  The midpoint (in data value) of the diverging scale.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
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
fun scaleFillGradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGradient2(
    aesthetic = Aes.FILL,
    low = low, mid = mid, high = high,
    midpoint = midpoint,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Defines smooth color gradient between multiple colors for the specified aesthetics.
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param colors Gradient colors list.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleGradientN(
    aesthetic: Any,
    colors: List<String>,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = aesthetic,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            COLORS to colors,
            SCALE_MAPPER_KIND to COLOR_GRADIENTN
        )
    )
)

/**
 * Defines smooth color gradient between multiple colors for `fill` aesthetic.
 *
 * @param colors Gradient colors list.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
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
fun scaleFillGradientN(
    colors: List<String>,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGradientN(
    aesthetic = Aes.FILL,
    colors = colors,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Defines diverging color gradient (low-mid-high) for `color` aesthetic.
 *
 * @param low Color for low end of gradient.
 * @param high Color for high end of gradient.
 * @param midpoint default = 0.0.
 *  The midpoint (in data value) of the diverging scale.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleColorGradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGradient2(
    aesthetic = Aes.COLOR,
    low = low, mid = mid, high = high,
    midpoint = midpoint,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Defines smooth color gradient between multiple colors for `color` aesthetic.
 *
 * @param colors Gradient colors list.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleColorGradientN(
    colors: List<String>,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGradientN(
    aesthetic = Aes.COLOR,
    colors = colors,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Sequential grey color scale for the specified aesthetics.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param start Gray value at low end of palette in range `[0,1]`.
 * @param end Gray value at high end of palette in range `[0,1]`.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleGrey(
    aesthetic: Any,
    start: Number? = null,
    end: Number? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
): Scale {
    checkGreyScaleStartEnd(start, end)
    return Scale(
        aesthetic = aesthetic,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        naValue = naValue,
        format = format,
        guide = guide,
        trans = trans,
        otherOptions = Options(
            mapOf(
                START to start,
                END to end,
                SCALE_MAPPER_KIND to COLOR_GREY
            )
        )
    )
}

/**
 * Sequential grey color scale for `fill` aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * ## Examples
 *
 * - [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)
 *
 * @param start Gray value at low end of palette in range `[0,1]`.
 * @param end Gray value at high end of palette in range `[0,1]`.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
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
fun scaleFillGrey(
    start: Number? = null,
    end: Number? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGrey(
    aesthetic = Aes.FILL,
    start = start, end = end,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Sequential grey color scale for `color` aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param start Gray value at low end of palette in range `[0,1]`.
 * @param end Gray value at high end of palette in range `[0,1]`.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleColorGrey(
    start: Number? = null,
    end: Number? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleGrey(
    aesthetic = Aes.COLOR,
    start = start, end = end,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Qualitative color scale with evenly spaced hues for the specified aesthetics.
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param h Range of hues, in `[0, 360]`.
 * @param c Chroma (intensity of color), maximum value varies depending on.
 * @param l Luminance (lightness), in `[0, 100]`.
 * @param hstart Hue to start at.
 * @param direction default = 1.
 *  Direction to travel around the color wheel, 1 = clockwise, -1 = counter-clockwise.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 */
fun scaleHue(
    aesthetic: Any,
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
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = aesthetic,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to hstart,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to COLOR_HUE
        )
    )
)

/**
 * Qualitative color scale with evenly spaced hues for `fill` aesthetic.
 *
 * ## Examples
 *
 * - [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)
 *
 * @param h Range of hues, in `[0,360]`.
 * @param c Chroma (intensity of color), maximum value varies depending on.
 * @param l Luminance (lightness), in `[0,100]`.
 * @param hstart Hue to start at.
 * @param direction default = 1. Direction to travel around the color wheel, 1 = clockwise, -1 = counter-clockwise.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
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
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleHue(
    aesthetic = Aes.FILL,
    h = h, c = c, l = l,
    hstart = hstart,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)

/**
 * Qualitative color scale with evenly spaced hues for `color` aesthetic.
 *
 * @param h Range of hues, in `[0, 360]`.
 * @param c Chroma (intensity of color), maximum value varies depending on.
 * @param l Luminance (lightness), in `[0, 100]`.
 * @param hstart Hue to start at.
 * @param direction default = 1.
 *  Direction to travel around the color wheel, 1 = clockwise, -1 = counter-clockwise.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use `null` to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
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
    format: String? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleHue(
    aesthetic = Aes.COLOR,
    h = h, c = c, l = l,
    hstart = hstart,
    direction = direction,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    format = format,
    guide = guide,
    trans = trans
)
