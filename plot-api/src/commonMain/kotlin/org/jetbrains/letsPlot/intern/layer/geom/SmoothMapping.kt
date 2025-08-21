/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomSmooth()][org.jetbrains.letsPlot.geom.geomSmooth].
 *
 * @param x X-axis value.
 * @param y Predicted (smoothed) value.
 * @param ymin Lower pointwise confidence interval around the mean.
 * @param ymax Upper pointwise confidence interval around the mean.
 * @param size Lines width.
 * @param linetype Type of the line for conditional mean.
 * @param color Color of the geometry.
 * @param fill Filling color for the confidence interval around the line.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class SmoothMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var alpha: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : SmoothAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<SmoothAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}

