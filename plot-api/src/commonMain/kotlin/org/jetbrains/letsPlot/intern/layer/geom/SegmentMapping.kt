/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomSegment()][org.jetbrains.letsPlot.geom.geomSegment].
 *
 * @param x X-axis value for the segment start.
 * @param y Y-axis value for the segment start.
 * @param xend X-axis value for the segment end.
 * @param yend Y-axis value for the segment end.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Segment color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Segment width.
 * @param sizeStart Offset from the segment start coordinate.
 *  Usually equal to the size of the point object from which the segment starts to avoid overlapping with it.
 * @param sizeEnd Offset from the segment end coordinate.
 *  Usually equal to the size of the point object from which the segment ends to avoid overlapping with it.
 * @param strokeStart Offset from the segment start coordinate.
 *  Usually equal to the stroke of the point object from which the segment starts to avoid overlapping with it.
 * @param strokeEnd Offset from the segment end coordinate.
 *  Usually equal to the stroke of the point object from which the segment ends to avoid overlapping with it.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class SegmentMapping(
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
    override var paint_c: Any? = null
) : SegmentAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<SegmentAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


