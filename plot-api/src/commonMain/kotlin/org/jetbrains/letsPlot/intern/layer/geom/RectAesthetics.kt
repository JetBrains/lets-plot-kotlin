/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @param xmin X-axis value.
 * @param xmax X-axis value.
 * @param ymin Y-axis value.
 * @param ymax Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Lines width.
 * @param linetype Type of the line of border.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 */
interface RectAesthetics : OptionsCapsule {
    val xmin: Any?
    val xmax: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "xmin" to xmin,
        "xmax" to xmax,
        "ymin" to ymin,
        "ymax" to ymax,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size,
        "fill" to fill
    )
}