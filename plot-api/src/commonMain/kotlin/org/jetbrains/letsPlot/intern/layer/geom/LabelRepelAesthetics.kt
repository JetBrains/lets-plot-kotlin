/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomLabelRepel()][org.jetbrains.letsPlot.geom.geomLabelRepel].
 *
 * @property x X-axis value.
 * @property y Y-axis value.
 * @property label Text to add to plot.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property fill Background color of the label. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property size Font size.
 * @property family For more info see: [aesthetics.html#font-family](https://lets-plot.org/kotlin/aesthetics.html#font-family).
 * @property fontface For more info see: [aesthetics.html#font-face](https://lets-plot.org/kotlin/aesthetics.html#font-face).
 * @property hjust horizontal text alignment relative to the x-coordinate. Possible values: 0 or "left" - left-aligned (text starts at x), 0.5 or "middle" (default) - text is centered on x, 1 or "right" - right-aligned (text ends at x). There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center).
 * @property vjust vertical text alignment relative to the y-coordinate. Possible values: 0 or "bottom" - bottom-aligned (bottom of text at y), 0.5 or "center" (default) - middle of text at y, 1 or "top" - top-aligned (top of text at y). There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center).
 * @property angle Label rotation angle in degrees.
 * @property lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @property shape Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @property pointSize A value representing the visual size of the point associated with the label. Set to 0 to prevent label repulsion from data points.
 * @property pointStroke Width of the point border.
 * @property segmentColor Color of the line segment connecting the label to the point.
 * @property segmentSize Width of the line segment connecting the label to the point.
 * @property segmentAlpha Transparency level of the line segment. Understands numbers between 0 and 1.
 * @property linetype Type of the line. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 */
interface LabelRepelAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val label: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
    val family: Any?
    val fontface: Any?
    val hjust: Any?
    val vjust: Any?
    val angle: Any?
    val lineheight: Any?
    val shape: Any?
    val pointSize: Any?
    val pointStroke: Any?
    val segmentColor: Any?
    val segmentSize: Any?
    val segmentAlpha: Any?
    val linetype: Any?

    override fun seal() = Options.of(
        Aes.X.name to x,
        Aes.Y.name to y,
        Aes.LABEL.name to label,
        Aes.ALPHA.name to alpha,
        Aes.COLOR.name to color,
        Aes.FILL.name to fill,
        Aes.SIZE.name to size,
        Aes.FAMILY.name to family,
        Aes.FONTFACE.name to fontface,
        Aes.HJUST.name to hjust,
        Aes.VJUST.name to vjust,
        Aes.ANGLE.name to angle,
        Aes.LINEHEIGHT.name to lineheight,
        Aes.SHAPE.name to shape,
        Aes.POINT_SIZE.name to pointSize,
        Aes.POINT_STROKE.name to pointStroke,
        Aes.SEGMENT_COLOR.name to segmentColor,
        Aes.SEGMENT_SIZE.name to segmentSize,
        Aes.SEGMENT_ALPHA.name to segmentAlpha,
        Aes.LINETYPE.name to linetype,
    )
}