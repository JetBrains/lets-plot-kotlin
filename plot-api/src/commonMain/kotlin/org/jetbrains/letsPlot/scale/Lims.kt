/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.Scale
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing

/**
 * Specifies data range for x and y axis.
 * Set limits if you want values to be consistent across multiple plots.
 * Setting limits will remove data outside of the limits.
 *
 * @param x The data range for x axis.
 *      Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *      Discrete scale: list of data values to display on axis, and their order.
 *
 * @param y The data range for y axis.
 *      Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *      Discrete scale: list of data values to display on axis, and their order.
 *
 */
@Suppress("SpellCheckingInspection")
fun lims(x: Any, y: Any): Feature {
    return xlim(x) + ylim(y)
}

/**
 * Specifies data range for x axis.
 * Set limits if you want values to be consistent across multiple plots.
 * Setting limits will remove data outside of the limits.
 *
 * @param limits The data range for x axis.
 *      Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *      Discrete scale: list of data values to display on axis, and their order.
 *
 */
@Suppress("SpellCheckingInspection")
fun xlim(limits: Any): Feature {
    return Scale(aesthetic = Aes.X, limits = SeriesStandardizing.toList(limits, "limits"))
}

/**
 * Specifies data range for y axis.
 * Set limits if you want values to be consistent across multiple plots.
 * Setting limits will remove data outside of the limits.
 *
 * @param limits The data range for y axis.
 *      Continuous scale: a pair of numbers providing limits of the scale. Use `null` to refer to default min/max.
 *      Discrete scale: list of data values to display on axis, and their order.
 *
 */
@Suppress("SpellCheckingInspection")
fun ylim(limits: Any): Feature {
    return Scale(aesthetic = Aes.Y, limits = SeriesStandardizing.toList(limits, "limits"))
}
