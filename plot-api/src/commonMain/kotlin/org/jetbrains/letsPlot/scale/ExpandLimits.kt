/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.commons.data.SeriesUtil.isFinite
import org.jetbrains.letsPlot.geom.geomBlank
import org.jetbrains.letsPlot.intern.Feature

/**
 * Expands the plot limits to include additional data values.
 *
 * This function extends the plot boundaries to encompass new data points,
 * whether a single value or multiple values are provided. It acts as a
 * thin wrapper around geomBlank().
 *
 * @param x Value (or values) that should be included in x-scale.
 * @param y Value (or values) that should be included in y-scale.
 * @param size
 * @param color
 * @param fill
 * @param alpha
 * @param shape
 *
 * @return Layer object. A result of the `geomBlank()` call.
 *
 * ## Notes
 *
 *  The data value (or values) could be a single value or an iterable or a Pair or Triple.
 *
 * ## Examples
 *
 * ToDo
 *
 */
fun expandLimits(
    x: Any? = null,
    y: Any? = null,
    size: Any? = null,
    color: Any? = null,
    fill: Any? = null,
    alpha: Any? = null,
    shape: Any? = null
): Feature {

    val standardized: Map<String, List<Any?>> = mapOf(
        "x" to standardize(x),
        "y" to standardize(y),
        "size" to standardize(size),
        "color" to standardize(color),
        "fill" to standardize(fill),
        "alpha" to standardize(alpha),
        "shape" to standardize(shape),
    )

    // Drop all undefined but keep x and y even if undefined.
    val cleaned = standardized.filter { (k, v) ->
        k == "x" || k == "y" || v.any { it != null && if (it is Number) isFinite(it.toDouble()) else true }
    }

    val maxLen = cleaned.values.maxBy { it.size }.size
    val data = cleaned.mapValues { (_, v) ->
        v + List(maxLen - v.size) { null }
    }

    return geomBlank {
        this.x = data.getValue("x")
        this.y = data.getValue("y")
        data["size"]?.let { this.size = it }
        data["color"]?.let { this.color = it }
        data["fill"]?.let { this.fill = it }
        data["alpha"]?.let { this.alpha = it }
        data["shape"]?.let { this.shape = it }
    }
}

private fun standardize(v: Any?): List<Any?> {
    return when (v) {
        is Iterable<*> -> v.toList()
        is Pair<*, *> -> listOf(v.first, v.second)
        is Triple<*, *, *> -> listOf(v.first, v.second, v.third)
        else -> listOf(v)
    }
}
