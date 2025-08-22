/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomSmooth()][org.jetbrains.letsPlot.geom.geomSmooth].
 *
 * @property x X-axis value.
 * @property y Predicted (smoothed) value.
 * @property ymin Lower pointwise confidence interval around the mean.
 * @property ymax Upper pointwise confidence interval around the mean.
 * @property size Lines width.
 * @property linetype Type of the line for conditional mean.
 * @property color Color of the geometry.
 * @property fill Filling color for the confidence interval around the line.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 */
interface SmoothAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val ymin: Any?
    val ymax: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "ymin" to ymin,
        "ymax" to ymax,
        "size" to size,
        "linetype" to linetype,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha
    )
}