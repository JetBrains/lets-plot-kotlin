/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot.scale

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale
import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.CHROMA
import jetbrains.datalore.plot.config.Option.Scale.DIRECTION
import jetbrains.datalore.plot.config.Option.Scale.END
import jetbrains.datalore.plot.config.Option.Scale.HIGH
import jetbrains.datalore.plot.config.Option.Scale.HUE_RANGE
import jetbrains.datalore.plot.config.Option.Scale.LOW
import jetbrains.datalore.plot.config.Option.Scale.LUMINANCE
import jetbrains.datalore.plot.config.Option.Scale.MID
import jetbrains.datalore.plot.config.Option.Scale.MIDPOINT
import jetbrains.datalore.plot.config.Option.Scale.SCALE_MAPPER_KIND
import jetbrains.datalore.plot.config.Option.Scale.START
import jetbrains.datalore.plot.config.Option.Scale.START_HUE


fun scale_fill_continuous(
    low: String, high: String,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.FILL,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            LOW to low,
            HIGH to high,
            SCALE_MAPPER_KIND to "color_gradient"
        )
    )
)

fun scale_color_continuous(
    low: String, high: String,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.COLOR,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            LOW to low,
            HIGH to high,
            SCALE_MAPPER_KIND to "color_gradient"
        )
    )
)

fun scale_fill_gradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = scale_fill_continuous(
    low, high,
    name, breaks, labels, limits, expand, na_value, guide, trans
)

fun scale_color_gradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = scale_color_continuous(
    low, high,
    name, breaks, labels, limits, expand, na_value, guide, trans
)

fun scale_fill_gradient2(
    low: String, mid: String, high: String, midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.FILL,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            LOW to low,
            HIGH to high,
            MID to mid,
            MIDPOINT to midpoint,
            SCALE_MAPPER_KIND to "color_gradient2"
        )
    )
)

fun scale_color_gradient2(
    low: String, mid: String, high: String, midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.COLOR,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            LOW to low,
            HIGH to high,
            MID to mid,
            MIDPOINT to midpoint,
            SCALE_MAPPER_KIND to "color_gradient2"
        )
    )
)

fun scale_fill_grey(
    start: Int?, end: Int?, direction: Int?,  // start, end: [0..100]. direction < 0 - reversed
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.FILL,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            START to start,
            END to end,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_grey"
        )
    )
)

fun scale_color_grey(
    start: Int?, end: Int?, direction: Int?,  // start, end: [0..100]. direction < 0 - reversed
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.COLOR,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            START to start,
            END to end,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_grey"
        )
    )
)


fun scale_fill_hue(
    h: Int? = null,
    c: Int? = null,
    l: Int? = null,
    h_start: Int? = null,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.FILL,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to h_start,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_hue"
        )
    )
)


fun scale_color_hue(
    h: Int?, c: Int?, l: Int?, h_start: Int?, direction: Int?,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    Aes.COLOR,
    name, breaks, labels, limits, expand, na_value, guide, trans,
    Options(
        mapOf(
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to h_start,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_hue"
        )
    )
)
