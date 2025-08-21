/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomCrossbar()][org.jetbrains.letsPlot.geom.geomCrossbar].
 *
 * @param x X-axis coordinates for vertical bar / position of median for horizontal bar.
 * @param ymin Lower bound for vertical bar.
 * @param ymax Upper bound for vertical bar.
 * @param y Y-axis coordinates for horizontal bar / position of median for vertical bar.
 * @param xmin Lower bound for horizontal bar.
 * @param xmax Upper bound for horizontal bar.
 * @param width Width of a bar. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the bars.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the lines.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Lines width.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class CrossBarMapping(
    override var x: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var y: Any? = null,
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var width: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : CrossBarAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<CrossBarAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}