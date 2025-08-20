/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomErrorBar()][org.jetbrains.letsPlot.geom.geomErrorBar].
 *
 * @param x X-axis coordinates for vertical error bar.
 * @param ymin Lower bound for the vertical error bar.
 * @param ymax Upper bound for the vertical error bar.
 * @param width Width of whiskers for the vertical error bar. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the whiskers.
 * @param y Y-axis coordinates for horizontal error bar.
 * @param xmin Lower bound for the horizontal error bar.
 * @param xmax Upper bound for the horizontal error bar.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 */
interface ErrorBarAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val width: Any?
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
        "width" to width,
        "y" to y,
        "xmin" to xmin,
        "xmax" to xmax,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}