/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomPointRange()][org.jetbrains.letsPlot.geom.geomPointRange].
 *
 * @property x X-axis coordinates for vertical interval / position of mid-point for horizontal interval.
 * @property y Y-axis coordinates for horizontal interval / position of mid-point for vertical interval.
 * @property ymin Lower bound for vertical interval.
 * @property ymax Upper bound for vertical interval.
 * @property xmin Lower bound for horizontal interval.
 * @property xmax Upper bound for horizontal interval.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property fill Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property linetype Type of the line of border. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @property shape Shape of the mid-point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @property size Lines width, size of mid-point.
 * @property stroke Width of the shape border. Applied only to the shapes having border.
 * @property linewidth Line width.
 */
interface PointRangeAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val ymin: Any?
    val ymax: Any?
    val xmin: Any?
    val xmax: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val shape: Any?
    val size: Any?
    val stroke: Any?
    val linewidth: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "ymin" to ymin,
        "ymax" to ymax,
        "xmin" to xmin,
        "xmax" to xmax,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "shape" to shape,
        "size" to size,
        "stroke" to stroke,
        "linewidth" to linewidth,
    )
}