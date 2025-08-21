/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomCrossbar()][org.jetbrains.letsPlot.geom.geomCrossbar].
 *
 * @property x X-axis coordinates for vertical bar / position of median for horizontal bar.
 * @property ymin Lower bound for vertical bar.
 * @property ymax Upper bound for vertical bar.
 * @property y Y-axis coordinates for horizontal bar / position of median for vertical bar.
 * @property xmin Lower bound for horizontal bar.
 * @property xmax Upper bound for horizontal bar.
 * @property width Width of a bar. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the bars.
 * @property alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @property color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property linetype Type of the lines.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @property size Lines width.
 */
interface CrossBarAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val y: Any?
    val xmin: Any?
    val xmax: Any?
    val width: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "y" to y,
        "xmin" to xmin,
        "xmax" to xmax,
        "width" to width,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size
    )
}