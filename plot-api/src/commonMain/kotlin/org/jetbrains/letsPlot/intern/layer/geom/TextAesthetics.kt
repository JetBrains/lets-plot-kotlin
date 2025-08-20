/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomText()][org.jetbrains.letsPlot.geom.geomText].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param label Text to add to plot.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Text color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Text size.
 * @param family Text font family.
 *  For more info see: [aesthetics.html#font-family](https://lets-plot.org/kotlin/aesthetics.html#font-family).
 * @param fontface Text font face.
 *  For more info see: [aesthetics.html#font-face](https://lets-plot.org/kotlin/aesthetics.html#font-face).
 * @param hjust Horizontal text alignment.
 * @param vjust Vertical text alignment.
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier applied to the font size in the case of multi-line text.
 */

interface TextAesthetics : OptionsCapsule {
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

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "label" to label,
        "alpha" to alpha,
        "color" to color,
        "size" to size,
        "family" to family,
        "fontface" to fontface,
        "hjust" to hjust,
        "vjust" to vjust,
        "angle" to angle,
        "lineheight" to lineheight
    )
}