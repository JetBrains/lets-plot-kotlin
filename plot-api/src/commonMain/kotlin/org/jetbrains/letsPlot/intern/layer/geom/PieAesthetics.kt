/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomPie()][org.jetbrains.letsPlot.geom.geomPie].
 *
 * @property x X-axis value.
 * @property y Y-axis value.
 * @property slice Values associated to pie sectors.
 * @property explode Values to explode slices away from their center point, detaching it from the main pie.
 * @property size Pie diameter.
 * @property fill Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of inner and outer arcs of pie sector. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property stroke Width of inner and outer arcs of pie sector.
 */
interface PieAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val slice: Any?
    val explode: Any?
    val size: Any?
    val fill: Any?
    val alpha: Any?
    val color: Any?
    val stroke: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "slice" to slice,
        "explode" to explode,
        "size" to size,
        "fill" to fill,
        "alpha" to alpha,
        "color" to color,
        "stroke" to stroke
    )
}