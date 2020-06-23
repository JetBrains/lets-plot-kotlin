/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot.scale

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
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale


fun scale_fill_continuous(
    low: String, high: String,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            LOW to low,
            HIGH to high,
            SCALE_MAPPER_KIND to "color_gradient"
        )
    )
)

fun scale_color_continuous(
    low: String? = null, high: String? = null,           // ToDo: string or Color
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
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
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = scale_fill_continuous(
    low, high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans
)

fun scale_color_gradient(
    low: String, high: String,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = scale_color_continuous(
    low, high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans
)

fun scale_fill_gradient2(
    low: String, mid: String, high: String, midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
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
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            LOW to low,
            HIGH to high,
            MID to mid,
            MIDPOINT to midpoint,
            SCALE_MAPPER_KIND to "color_gradient2"
        )
    )
)

// ToDo: since Lets-Plot v.1.4.3 params start, end are in range [0..1].
fun scale_fill_grey(
    start: Double? = null,
    end: Double? = null,   // start, end: [0..100].
    direction: Int?,        // direction < 0 - reversed
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
): Scale {
    start?.let { require(start in (0.0..100.0)) { "'start' must be in range: [0,100]: $start" } }
    end?.let { require(end in (0.0..100.0)) { "'end' must be in range: [0,100]: $end" } }

    return Scale(
        aesthetic = Aes.FILL,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits?.toList(),
        expand = expand,
        naValue = naValue,
        guide = guide,
        trans = trans,
        otherOptions = Options(
            mapOf(
                START to start,
                END to end,
                DIRECTION to direction,
                SCALE_MAPPER_KIND to "color_grey"
            )
        )
    )
}

// ToDo: since Lets-Plot v.1.4.3 params start, end are in range [0..1].
fun scale_color_grey(
    start: Double? = null,
    end: Double? = null,   // start, end: [0..100].
    direction: Int = 1,         // direction < 0 - reversed
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
): Scale {
    start?.let { require(start in (0.0..100.0)) { "'start' must be in range: [0,100]: $start" } }
    end?.let { require(end in (0.0..100.0)) { "'end' must be in range: [0,100]: $end" } }
    return Scale(
        aesthetic = Aes.COLOR,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits?.toList(),
        expand = expand,
        naValue = naValue,
        guide = guide,
        trans = trans,
        otherOptions = Options(
            mapOf(
                START to start,
                END to end,
                DIRECTION to direction,
                SCALE_MAPPER_KIND to "color_grey"
            )
        )
    )
}

fun scale_fill_hue(
    h: Int? = null,
    c: Int? = null,
    l: Int? = null,
    @Suppress("SpellCheckingInspection")
    hstart: Int? = null,
    direction: Int? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to hstart,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_hue"
        )
    )
)

fun scale_color_hue(
    h: Int?, c: Int?, l: Int?,
    @Suppress("SpellCheckingInspection")
    hstart: Int?,
    direction: Int?,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            HUE_RANGE to h,
            CHROMA to c,
            LUMINANCE to l,
            START_HUE to hstart,
            DIRECTION to direction,
            SCALE_MAPPER_KIND to "color_hue"
        )
    )
)
