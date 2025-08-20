/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomErrorBar()][org.jetbrains.letsPlot.geom.geomErrorBar].
 *
 * @param x X-axis coordinates for vertical error bar.
 * @param ymin Lower bound for the vertical error bar.
 * @param ymax Upper bound for the vertical error bar.
 * @param width Width of whiskers for the vertical error bar. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the whiskers.
 * @param y Y-axis coordinates for horizontal error bar.
 * @param xmin Lower bound for the horizontal error bar.
 * @param xmax Upper bound for the horizontal error bar.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class ErrorBarMapping(
    override var x: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var width: Any? = null,
    override var y: Any? = null,
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : ErrorBarAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<ErrorBarAesthetics>.seal() + groupOption() +
            super<PaintAesthetics>.seal()
}