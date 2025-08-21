/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomLollipop()][org.jetbrains.letsPlot.geom.geomLollipop].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param size Size of the point.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param linewidth Stick width.
 * @param color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color. Is applied only to the points of shapes having inner area. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param shape Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param linetype Type of the stick line. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 */
interface LollipopAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val size: Any?
    val stroke: Any?
    val linewidth: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?
    val shape: Any?
    val linetype: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "size" to size,
        "stroke" to stroke,
        "linewidth" to linewidth,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha,
        "shape" to shape,
        "linetype" to linetype
    )
}