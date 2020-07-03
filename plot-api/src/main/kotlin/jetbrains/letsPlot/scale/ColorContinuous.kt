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
    low: String? = null, high: String? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
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
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
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
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scale_fill_continuous(
    low, high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
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
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = scale_color_continuous(
    low, high,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    guide = guide,
    trans = trans
)

fun scale_fill_gradient2(
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
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
    low: String, mid: String, high: String,
    midpoint: Double = 0.0,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
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

fun scale_fill_grey(
    start: Number? = null,
    end: Number? = null,
    direction: Int? = null,        // direction < 0 - reversed
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
): Scale {
    start?.let { require(start.toDouble() in (0.0..1.0)) { "'start' must be in range: [0,1]: $start" } }
    end?.let { require(end.toDouble() in (0.0..1.0)) { "'end' must be in range: [0,1]: $end" } }

    return Scale(
        aesthetic = Aes.FILL,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits?.toList(),
        naValue = naValue,
        guide = guide,
        trans = trans,
        otherOptions = Options(
            mapOf(
                // ToDo: scale_xxx_grey
                // Tmp scale values back to the old range [1,100]
                // Remove when next Lets-Plot RC is released
                START to if(start != null) start.toDouble() * 100 else null,
                END to if(end != null) end.toDouble() * 100 else null,
//                START to start,
//                END to end,
                DIRECTION to direction,
                SCALE_MAPPER_KIND to "color_grey"
            )
        )
    )
}

fun scale_color_grey(
    start: Number? = null,
    end: Number? = null,
    direction: Int? = null,         // direction < 0 - reversed
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
): Scale {
    start?.let { require(start.toDouble() in (0.0..1.0)) { "'start' must be in range: [0,1]: $start" } }
    end?.let { require(end.toDouble() in (0.0..1.0)) { "'end' must be in range: [0,1]: $end" } }

    return Scale(
        aesthetic = Aes.COLOR,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits?.toList(),
        naValue = naValue,
        guide = guide,
        trans = trans,
        otherOptions = Options(
            mapOf(
                // ToDo: scale_xxx_grey
                // Tmp scale values back to the old range [1,100]
                // Remove when next Lets-Plot RC is released
                START to if(start != null) start.toDouble() * 100 else null,
                END to if(end != null) end.toDouble() * 100 else null,
//                START to start,
//                END to end,
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
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.FILL,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
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
    naValue: Any? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.COLOR,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
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
