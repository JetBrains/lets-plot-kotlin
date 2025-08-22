/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomAreaRidges()][org.jetbrains.letsPlot.geom.geomAreaRidges].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param height Height of the ridge. Assumed to be between 0 and 1, though this is not required.
 * @param quantile Quantile values to draw quantile lines and fill quantiles of the geometry by color.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line of border.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Defines line width.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class AreaRidgesMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var height: Any? = null,
    override var quantile: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : AreaRidgesAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<AreaRidgesAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}