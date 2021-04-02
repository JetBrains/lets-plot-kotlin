/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName", "SpellCheckingInspection")

package jetbrains.letsPlot.scale

// Position scales

@Deprecated("", ReplaceWith("scaleXDiscrete(data, name, breaks, labels, limits, expand, naValue, format, reverse)"))
fun scale_x_discrete(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    reverse: Boolean? = null
) = scaleXDiscrete(name, breaks, labels, limits, expand, naValue, format, reverse)

@Deprecated("", ReplaceWith("scaleYDiscrete(data, name, breaks, labels, limits, expand, naValue, format, reverse)"))
fun scale_y_discrete(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    reverse: Boolean? = null
) = scaleYDiscrete(name, breaks, labels, limits, expand, naValue, format, reverse)

@Deprecated("", ReplaceWith("scaleXDiscreteReversed(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_x_discrete_reversed(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleXDiscreteReversed(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleYDiscreteReversed(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_y_discrete_reversed(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleYDiscreteReversed(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleXContinuous(name, breaks, labels, limits, expand, naValue, format, trans)"))
fun scale_x_continuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    trans: String? = null,
) = scaleXContinuous(name, breaks, labels, limits, expand, naValue, format, trans)

@Deprecated("", ReplaceWith("scaleYContinuous(name, breaks, labels, limits, expand, naValue, format, trans)"))
fun scale_y_continuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
    trans: String? = null,
) = scaleYContinuous(name, breaks, labels, limits, expand, naValue, format, trans)

@Deprecated("", ReplaceWith("scaleXLog10(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_x_log10(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleXLog10(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleYLog10(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_y_log10(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleYLog10(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleXReverse(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_x_reverse(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleXReverse(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleYReverse(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_y_reverse(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleYReverse(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleXSqrt(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_x_sqrt(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleXSqrt(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleYSqrt(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_y_sqrt(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Number>? = null,
    naValue: Number? = null,
    format: String? = null,
) = scaleYSqrt(name, breaks, labels, limits, expand, naValue, format)

// Identity scales

@Deprecated("", ReplaceWith("scaleColorIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_color_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleColorIdentity(name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleFillIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_fill_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleFillIdentity(name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleShapeIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_shape_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleShapeIdentity(name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleLinetypeIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_linetype_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleLinetypeIdentity(name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleAlphaIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_alpha_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = scaleAlphaIdentity(name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleSizeIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_size_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = scaleSizeIdentity(name, breaks, labels, limits, naValue, guide)

// Color scales

@Deprecated("", ReplaceWith("scaleColorManual(values, name, breaks, labels, limits, naValue, guide)"))
fun scale_color_manual(
    values: List<Any>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleColorManual(values, name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleColorDiscrete(direction, name, breaks, labels, limits, naValue, guide)"))
fun scale_color_discrete(
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleColorDiscrete(direction, name, breaks, labels, limits, naValue, guide)

@Deprecated("", ReplaceWith("scaleColorContinuous(low, high, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_color_continuous(
    low: String? = null, high: String? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorContinuous(low, high, name, breaks, labels, limits, naValue, guide, trans)

@Deprecated("", ReplaceWith("scaleColorGradient(low, high, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_color_gradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorGradient(low, high, name, breaks, labels, limits, naValue, guide, trans)

@Deprecated("", ReplaceWith("scaleColorGradient2(low, mid, high, midpoint, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_color_gradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorGradient2(low, mid, high, midpoint, name, breaks, labels, limits, naValue, guide, trans)

@Deprecated("", ReplaceWith("scaleColorHue(h, c, l, hstart, direction, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_color_hue(
    h: Pair<Int, Int>? = null,
    c: Int? = null,
    l: Int? = null,
    hstart: Int? = null,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorHue(h, c, l, hstart, direction, name, breaks, labels, limits, naValue, guide, trans)

@Deprecated("", ReplaceWith("scaleColorGrey(start, end, direction, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_color_grey(
    start: Number? = null,
    end: Number? = null,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorGrey(start, end, direction, name, breaks, labels, limits, naValue, guide, trans)

@Deprecated("", ReplaceWith("scaleColorBrewer(type, palette, direction, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_color_brewer(
    type: String? = null,
    palette: Any? = null,
    direction: Number? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleColorBrewer(type, palette, direction, name, breaks, labels, limits, naValue, guide, trans)

// Fill scales

// Size scales

// Shape scales

// Linetype scales

// Alpha scales

// Datetime scales

// Scale guides
@Deprecated("", ReplaceWith("guideLegend(nrow, ncol, byRow)"))
fun guide_legend(
    nrow: Int? = null,
    ncol: Int? = null,
    byRow: Boolean? = null
) = guideLegend(nrow, ncol, byRow)

@Deprecated("", ReplaceWith("guideColorbar(barWidth, barHeight, nbin)"))
fun guide_colorbar(
    barWidth: Number? = null,
    barHeight: Number? = null,
    nbin: Int? = null
) = guideColorbar(barWidth, barHeight, nbin)