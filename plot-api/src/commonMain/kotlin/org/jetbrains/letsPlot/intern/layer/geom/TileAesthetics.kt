/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomTile()][org.jetbrains.letsPlot.geom.geomTile].
 *
 * @property x X-axis coordinates of the center of rectangles.
 * @property y Y-axis coordinates of the center of rectangles.
 * @property width Width of a tile. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the tiles.
 * @property height Height of a tile. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the tiles.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry.
 * @property fill Fill color.
 * @property linetype Type of the line.
 * @property size Line width, default = 0 (i.e., tiles outline initially is not visible).
 */
interface TileAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val width: Any?
    val height: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "width" to width,
        "height" to height,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size
    )
}