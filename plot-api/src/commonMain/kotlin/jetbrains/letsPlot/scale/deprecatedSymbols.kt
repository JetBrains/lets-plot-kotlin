/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName", "SpellCheckingInspection")

package jetbrains.letsPlot.scale

// Position scales

@Deprecated("", ReplaceWith("scaleXDiscrete(name, breaks, labels, limits, expand, naValue, format, reverse)"))
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

@Deprecated("", ReplaceWith("scaleYDiscrete(name, breaks, labels, limits, expand, naValue, format, reverse)"))
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
) = scaleColorIdentity(name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleFillIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_fill_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleFillIdentity(name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleShapeIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_shape_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleShapeIdentity(name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleLinetypeIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_linetype_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleLinetypeIdentity(name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleAlphaIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_alpha_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = scaleAlphaIdentity(name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleSizeIdentity(name, breaks, labels, limits, naValue, guide)"))
fun scale_size_identity(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = scaleSizeIdentity(name, breaks, labels, limits, naValue, null, guide)

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
) = scaleColorManual(values, name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleColorDiscrete(direction, name, breaks, labels, limits, naValue, guide)"))
fun scale_color_discrete(
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleColorDiscrete(direction, name, breaks, labels, limits, naValue, null, guide)

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
) = scaleColorContinuous(low, high, name, breaks, labels, limits, naValue, null, guide, trans)

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
) = scaleColorGradient(low, high, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleColorGradient2(low, mid, high, midpoint, name, breaks, labels, limits, naValue, guide, trans)")
)
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
) = scaleColorGradient2(low, mid, high, midpoint, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleColorGrey(start, end, direction, name, breaks, labels, limits, naValue, guide, trans)")
)
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
) = scaleColorGrey(start, end, direction, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleColorHue(h, c, l, hstart, direction, name, breaks, labels, limits, naValue, guide, trans)")
)
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
) = scaleColorHue(h, c, l, hstart, direction, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleColorBrewer(type, palette, direction, name, breaks, labels, limits, naValue, guide, trans)")
)
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
) = scaleColorBrewer(type, palette, direction, name, breaks, labels, limits, naValue, null, guide, trans)

// Fill scales

@Deprecated("", ReplaceWith("scaleFillManual(values, name, breaks, labels, limits, naValue, guide)"))
fun scale_fill_manual(
    values: List<Any>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleFillManual(values, name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleFillDiscrete(direction, name, breaks, labels, limits, naValue, guide)"))
fun scale_fill_discrete(
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleFillDiscrete(direction, name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleFillContinuous(low, high, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_fill_continuous(
    low: String? = null, high: String? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleFillContinuous(low, high, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated("", ReplaceWith("scaleFillGradient(low, high, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_fill_gradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleFillGradient(low, high, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleFillGradient2(low, mid, high, midpoint, name, breaks, labels, limits, naValue, guide, trans)")
)
fun scale_fill_gradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleFillGradient2(low, mid, high, midpoint, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleFillGrey(start, end, direction, name, breaks, labels, limits, naValue, guide, trans)")
)
fun scale_fill_grey(
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
) = scaleFillGrey(start, end, direction, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleFillHue(h, c, l, hstart, direction, name, breaks, labels, limits, naValue, guide, trans)")
)
fun scale_fill_hue(
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
) = scaleFillHue(h, c, l, hstart, direction, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated(
    "",
    ReplaceWith("scaleFillBrewer(type, palette, direction, name, breaks, labels, limits, naValue, guide, trans)")
)
fun scale_fill_brewer(
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
) = scaleFillBrewer(type, palette, direction, name, breaks, labels, limits, naValue, null, guide, trans)

// Size scales

@Deprecated("", ReplaceWith("scaleSizeManual(values, name, breaks, labels, limits, naValue, guide)"))
fun scale_size_manual(
    values: List<Number>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = scaleSizeManual(values, name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleSize(range, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_size(
    range: Pair<Number, Number>? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Number? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleSize(range, name, breaks, labels, limits, naValue, null, guide, trans)

@Deprecated("", ReplaceWith("scaleSizeArea(maxSize, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_size_area(
    maxSize: Number? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Number? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleSizeArea(maxSize, name, breaks, labels, limits, naValue, null, guide, trans)

// Shape scales

@Deprecated("", ReplaceWith("scaleShapeManual(values, name, breaks, labels, limits, naValue, guide)"))
fun scale_shape_manual(
    values: List<Number>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleShapeManual(values, name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleShape(solid, name, breaks, labels, limits, naValue, guide)"))
fun scale_shape(
    solid: Boolean? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleShape(solid, name, breaks, labels, limits, naValue, null, guide)

// Linetype scales

@Deprecated("", ReplaceWith("scaleLinetypeManual(values, name, breaks, labels, limits, naValue, guide)"))
fun scale_linetype_manual(
    values: List<Number>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = scaleLinetypeManual(values, name, breaks, labels, limits, naValue, null, guide)

// Alpha scales

@Deprecated("", ReplaceWith("scaleAlphaManual(values, name, breaks, labels, limits, naValue, guide)"))
fun scale_alpha_manual(
    values: List<Double>,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Number? = null,
    guide: Any? = null
) = scaleAlphaManual(values, name, breaks, labels, limits, naValue, null, guide)

@Deprecated("", ReplaceWith("scaleAlpha(range, name, breaks, labels, limits, naValue, guide, trans)"))
fun scale_alpha(
    range: Pair<Number, Number>? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Number? = null,
    guide: Any? = null,
    trans: String? = null
) = scaleAlpha(range, name, breaks, labels, limits, naValue, null, guide, trans)

// Datetime scales

@Deprecated("", ReplaceWith("scaleXDateTime(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_x_datetime(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Any?, Any?>? = null,
    expand: List<Number>? = null,
    naValue: Any? = null,
    format: String? = null,
) = scaleXDateTime(name, breaks, labels, limits, expand, naValue, format)

@Deprecated("", ReplaceWith("scaleYDateTime(name, breaks, labels, limits, expand, naValue, format)"))
fun scale_y_datetime(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Any?, Any?>? = null,
    expand: List<Number>? = null,
    naValue: Any? = null,
    format: String? = null,
) = scaleYDateTime(name, breaks, labels, limits, expand, naValue, format)

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