/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomCurve()][org.jetbrains.letsPlot.geom.geomCurve].
 *
 * @param x Starting X-axis coordinate.
 * @param y Starting Y-axis coordinate.
 * @param xend Ending X-axis coordinate.
 * @param yend Ending Y-axis coordinate.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Curve color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Curve line type.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Curve width.
 * @param sizeStart Starting width of the curve.
 * @param sizeEnd Ending width of the curve.
 * @param strokeStart Starting stroke width.
 * @param strokeEnd Ending stroke width.
 * @param group Grouping key. Observations with the same value form one line.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, linetype).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class CurveMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var xend: Any? = null,
    override var yend: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var sizeStart: Any? = null,
    override var sizeEnd: Any? = null,
    override var strokeStart: Any? = null,
    override var strokeEnd: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null,
) : CurveAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<CurveAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}

