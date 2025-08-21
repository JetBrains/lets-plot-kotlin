/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomSegment()][org.jetbrains.letsPlot.geom.geomSegment].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param xend X-axis value.
 * @param yend Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param sizeStart Offset from the segment start coordinate. Usually equal to the size of the point object from which the segment starts to avoid overlapping with it.
 * @param sizeEnd Offset from the segment end coordinate. Usually equal to the size of the point object from which the segment ends to avoid overlapping with it.
 * @param strokeStart Offset from the segment start coordinate. Usually equal to the stroke of the point object from which the segment starts to avoid overlapping with it.
 * @param strokeEnd Offset from the segment end coordinate. Usually equal to the stroke of the point object from which the segment ends to avoid overlapping with it.
 */
interface SegmentAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val xend: Any?
    val yend: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?
    val sizeStart: Any?
    val sizeEnd: Any?
    val strokeStart: Any?
    val strokeEnd: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "xend" to xend,
        "yend" to yend,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size,
        "size_start" to sizeStart,
        "size_end" to sizeEnd,
        "stroke_start" to strokeStart,
        "stroke_end" to strokeEnd
    )
}