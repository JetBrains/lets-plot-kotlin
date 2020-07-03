/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.letsPlot.intern.Scale

/**
 * Continuous scale for x axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 *      Set limits if you want values to be consistent across multiple plots.
 *      Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
@Suppress("FunctionName")
fun scale_x_continuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Any>? = null,
    naValue: Any? = null,
    trans: String? = null
): Scale {
    expand?.let { require(expand.size in (1..2)) { "'expand' can contain no more than two values: $expand" } }
    expand?.let { require(expand.all { it is Number }) { "'expand' must contain numbers: $expand" } }

    return Scale(
        aesthetic = Aes.X,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits?.toList(),
        expand = expand,
        naValue = naValue,
        trans = trans
    )
}

/**
 * Continuous scale for y axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A pair of numbers specifying the data range for the scale.
 *      Use null to refer to default min/max.
 *      Set limits if you want values to be consistent across multiple plots.
 *      Setting limits will remove data outside of the limits.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0.05, additive = 0.
 * @param naValue Missing values will be replaced with this value.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
@Suppress("FunctionName")
fun scale_y_continuous(
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: List<Any>? = null,
    naValue: Any? = null,
    trans: String? = null
): Scale {
    expand?.let { require(expand.size in (1..2)) { "'expand' can contain no more than two values: $expand" } }
    expand?.let { require(expand.all { it is Number }) { "'expand' must contain numbers: $expand" } }

    return Scale(
        aesthetic = Aes.Y,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits?.toList(),
        expand = expand,
        naValue = naValue,
        trans = trans
    )
}