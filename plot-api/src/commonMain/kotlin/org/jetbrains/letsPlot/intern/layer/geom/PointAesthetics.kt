/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface PointAesthetics : OptionsCapsule {
    /** X-axis value. */
    val x: Any?
    /** Y-axis value. */
    val y: Any?
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    val alpha: Any?
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val color: Any?
    /** Color to paint shape's inner points. Is applied only to the points of shapes having inner points. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val fill: Any?
    /** Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes). */
    val shape: Any?
    /** Size of the point. */
    val size: Any?
    /** Width of the shape border. Applied only to the shapes having border. */
    val stroke: Any?
    /** Rotation angle of the shape, in degrees. */
    val angle: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "shape" to shape,
        "size" to size,
        "stroke" to stroke,
        "angle" to angle
    )
}