/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomRaster()][org.jetbrains.letsPlot.geom.geomRaster].
 *
 * @property x X-axis coordinates of the center of rectangles.
 * @property y Coordinates of the center of rectangles.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property fill Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 */
interface RasterAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "fill" to fill
    )
}