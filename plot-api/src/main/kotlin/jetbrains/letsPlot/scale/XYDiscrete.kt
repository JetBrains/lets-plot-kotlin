/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.letsPlot.intern.Scale

/**
 * Discrete scale for x axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks list of data values.
 *      A vector specifying values to display as ticks on axis.
 * @param labels A vector of labels (on ticks).
 * @param limits list of data values
 *      A vector specifying values to display on the axis and their order.
 *      Setting limits will remove data not included in the list.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0, additive = 0.6.
 * @param naValue Missing values will be replaced with this value.
 */
@Suppress("FunctionName")
fun scale_x_discrete(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Any>? = null,
    naValue: Any? = null
): Scale {
    expand?.let { require(expand.size in (1..2)) { "'expand' can contain no more than two values: $expand" } }
    expand?.let { require(expand.all { it is Number }) { "'expand' must contain numbers: $expand" } }

    return Scale(
        aesthetic = Aes.X,
        name = name,
        breaks = breaks,
        labels = labels,
        limits = limits,
        expand = expand,
        naValue = naValue
    )
}

/**
 * Discrete scale for y axis
 *
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks list of data values.
 *      A vector specifying values to display as ticks on axis.
 * @param labels A vector of labels (on ticks).
 * @param limits list of data values
 *      A vector specifying values to display on the axis and their order.
 *      Setting limits will remove data not included in the list.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 *      The vector size == 1 => only multiplicative expand (and additive expand by default).
 *      Defaults: multiplicative = 0, additive = 0.6.
 * @param naValue Missing values will be replaced with this value.
 */
@Suppress("FunctionName")
fun scale_y_discrete(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: List<Any>? = null,
    naValue: Any? = null
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
        naValue = naValue
    )
}
