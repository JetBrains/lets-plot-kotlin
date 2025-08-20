/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomPolygon()][org.jetbrains.letsPlot.geom.geomPolygon].
 *
 * @param x X-axis coordinates of the vertices of the polygon.
 * @param y Y-axis coordinates of the vertices of the polygon.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Polygon border color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Polygon fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Polygon border width.
 * @param linetype Polygon border line type.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 */

interface PolygonAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "size" to size,
        "linetype" to linetype,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha
    )
}