/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomTextRepel()][org.jetbrains.letsPlot.geom.geomTextRepel].
 *
 * @property x X-axis value.
 * @property y Y-axis value.
 * @property label Text label to display.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry.
 * @property size Font size.
 * @property family Font family.
 * @property fontface Font face.
 * @property hjust horizontal text alignment relative to the x-coordinate.
 * @property vjust vertical text alignment relative to the y-coordinate.
 * @property angle Text rotation angle in degrees.
 * @property lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @property shape Shape of the point.
 * @property pointSize A value representing the visual size of the point associated with the label.
 * @property pointStroke Width of the point border.
 * @property segmentColor Color of the line segment connecting the label to the point.
 * @property segmentSize Width of the line segment connecting the label to the point.
 * @property segmentAlpha Transparency level of the line segment. Understands numbers between 0 and 1.
 * @property linetype Type of the line.
 */
interface TextRepelAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val label: Any?
    val alpha: Any?
    val color: Any?
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