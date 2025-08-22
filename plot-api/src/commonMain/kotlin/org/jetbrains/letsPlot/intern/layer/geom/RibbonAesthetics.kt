/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomRibbon()][org.jetbrains.letsPlot.geom.geomRibbon].
 *
 * @property x X-axis coordinates for horizontal ribbon.
 * @property ymin Y-axis coordinates of the lower bound for horizontal ribbon.
 * @property ymax Y-axis coordinates of the upper bound for horizontal ribbon.
 * @property y Y-axis coordinates for vertical ribbon.
 * @property xmin X-axis coordinates of the lower bound for vertical ribbon.
 * @property xmax X-axis coordinates of the upper bound for vertical ribbon.
 * @property size Lines width.
 * @property linetype Type of the line of border. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @property color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property fill Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 */
interface RibbonAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val y: Any?
    val xmin: Any?
    val xmax: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "y" to y,
        "xmin" to xmin,
        "xmax" to xmax,
        "size" to size,
        "linetype" to linetype,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha
    )
}