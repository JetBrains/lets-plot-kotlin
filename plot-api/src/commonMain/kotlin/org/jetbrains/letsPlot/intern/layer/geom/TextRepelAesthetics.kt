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
 * @param x X-axis coordinate.
 * @param y Y-axis coordinate.
 * @param label Text to display.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Text color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Text size.
 * @param family Font family.
 * @param fontface Font face ("plain", "bold", "italic", "bold.italic").
 * @param hjust Horizontal text justification (0 = left, 0.5 = center, 1 = right).
 * @param vjust Vertical text justification (0 = bottom, 0.5 = center, 1 = top).
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier for multi-line text.
 * @param shape Point shape behind the text.
 *  For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param pointSize Point size behind the text.
 * @param pointStroke Point stroke width behind the text.
 * @param segmentColor Color of connecting segment from point to text.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param segmentSize Width of connecting segment from point to text.
 * @param segmentAlpha Opacity of connecting segment; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param linetype Line type of connecting segment.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
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