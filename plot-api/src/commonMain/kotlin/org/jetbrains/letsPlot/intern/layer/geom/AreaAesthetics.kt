/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomArea()][org.jetbrains.letsPlot.geom.geomArea].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Area border color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Area fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Area border line type.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Area border width.
 */

interface AreaAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size
    )
}