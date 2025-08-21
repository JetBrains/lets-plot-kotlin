/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomTextRepel()][org.jetbrains.letsPlot.geom.geomTextRepel].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param label Text label to display.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 * @param size Font size.
 * @param family Font family.
 * @param fontface Font face.
 * @param hjust horizontal text alignment relative to the x-coordinate.
 * @param vjust vertical text alignment relative to the y-coordinate.
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @param shape Shape of the point.
 * @param pointSize A value representing the visual size of the point associated with the label.
 * @param pointStroke Width of the point border.
 * @param segmentColor Color of the line segment connecting the label to the point.
 * @param segmentSize Width of the line segment connecting the label to the point.
 * @param segmentAlpha Transparency level of the line segment. Understands numbers between 0 and 1.
 * @param linetype Type of the line.
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