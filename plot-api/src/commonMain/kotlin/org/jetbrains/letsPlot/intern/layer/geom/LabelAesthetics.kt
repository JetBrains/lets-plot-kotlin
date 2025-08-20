/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomLabel()][org.jetbrains.letsPlot.geom.geomLabel].
 *
 * @param x X-axis coordinate.
 * @param y Y-axis coordinate.
 * @param label Text to display.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Label text color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Label background fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Label text size.
 * @param family Font family.
 * @param fontface Font face ("plain", "bold", "italic", "bold.italic").
 * @param hjust Horizontal text justification (0 = left, 0.5 = center, 1 = right).
 * @param vjust Vertical text justification (0 = bottom, 0.5 = center, 1 = top).
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier for multi-line text.
 * @param labelPadding Padding around label text.
 * @param labelR Radius for rounded label corners.
 * @param labelSize Label outline width.
 */
interface LabelAesthetics : OptionsCapsule {
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

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "label" to label,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "size" to size,
        "family" to family,
        "fontface" to fontface,
        "hjust" to hjust,
        "vjust" to vjust,
        "angle" to angle,
        "lineheight" to lineheight
    )
}