/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomLineRange()][org.jetbrains.letsPlot.geom.geomLineRange].
 *
 * @param x X-axis coordinates for vertical line range.
 * @param ymin Lower bound for vertical line range.
 * @param ymax Upper bound for vertical line range.
 * @param y Y-axis coordinates for horizontal line range
 * @param xmin Lower bound for horizontal line range.
 * @param xmax Upper bound for horizontal line range.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of geometry lines. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Lines width.
 */
interface LineRangeAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val y: Any?
    val xmin: Any?
    val xmax: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "y" to y,
        "xmin" to xmin,
        "xmax" to xmax,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}