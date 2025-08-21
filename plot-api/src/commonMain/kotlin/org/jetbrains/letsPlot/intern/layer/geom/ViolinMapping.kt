/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomViolin()][org.jetbrains.letsPlot.geom.geomViolin].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param violinWidth Density scaled for the violin plot, according to area, counts or to a constant maximum width.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 * @param fill Fill color.
 * @param linetype Type of the line of border.
 * @param size Lines width.
 * @param width Width of violin bounding box.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class ViolinMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var violinWidth: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var width: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : ViolinAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<ViolinAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}