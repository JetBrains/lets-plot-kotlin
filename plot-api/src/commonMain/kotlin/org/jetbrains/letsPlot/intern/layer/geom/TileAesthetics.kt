/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomTile()][org.jetbrains.letsPlot.geom.geomTile].
 *
 * @param x X-axis coordinates of the center of rectangles.
 * @param y Y-axis coordinates of the center of rectangles.
 * @param width Width of a tile. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the tiles.
 * @param height Height of a tile. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the tiles.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 * @param fill Fill color.
 * @param linetype Type of the line.
 * @param size Line width, default = 0 (i.e., tiles outline initially is not visible).
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