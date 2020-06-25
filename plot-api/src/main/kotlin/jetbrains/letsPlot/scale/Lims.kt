/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.letsPlot.intern.Feature

/**
 * Specifies data range for x and y axis.
 * Set limits if you want values to be consistent across multiple plots.
 * Setting limits will remove data outside of the limits.
 *
 * @param x A pair of numbers. The data range for x axis.
 *      Use null to refer to default min/max.
 * @param y A pair of numbers. The data range for y axis.
 *      Use null to refer to default min/max.
 */
@Suppress("SpellCheckingInspection")
fun lims(x: Pair<Number?, Number?>, y: Pair<Number?, Number?>): Feature {
    return xlim(x) + ylim(y)
}

/**
 * Specifies data range for x axis.
 * Set limits if you want values to be consistent across multiple plots.
 * Setting limits will remove data outside of the limits.
 *
 * @param limits A pair of numbers. The data range for x axis.
 *      Use null to refer to default min/max.
 */
@Suppress("SpellCheckingInspection")
fun xlim(limits: Pair<Number?, Number?>): Feature {
    return scale_x_continuous(limits = limits)
}

/**
 * Specifies data range for y axis.
 * Set limits if you want values to be consistent across multiple plots.
 * Setting limits will remove data outside of the limits.
 *
 * @param limits A pair of numbers. The data range for y axis.
 *      Use null to refer to default min/max.
 */
@Suppress("SpellCheckingInspection")
fun ylim(limits: Pair<Number?, Number?>): Feature {
    return scale_y_continuous(limits = limits)
}

///**
// * Specifies data values to dispay on  x axis, and their order.
// * Setting limits will remove data not included in the list.
// *
// * @param limits list of data values.
// */
//@Suppress("SpellCheckingInspection")
//fun xlim_discrete(limits: List<Any>): Feature {
//    return scale_x_dis(limits = limits)
//}

