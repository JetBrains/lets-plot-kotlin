/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomPie()][org.jetbrains.letsPlot.geom.geomPie].
 *
 * @param x X-axis center coordinate.
 * @param y Y-axis center coordinate.
 * @param slice Value that determines the size of each pie slice.
 * @param explode Distance to explode pie slice from center.
 * @param size Overall pie size.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Border color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param stroke Border width.
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
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