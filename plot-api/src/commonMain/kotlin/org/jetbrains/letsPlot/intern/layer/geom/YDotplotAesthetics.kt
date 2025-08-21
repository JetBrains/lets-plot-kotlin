/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomYDotplot()][org.jetbrains.letsPlot.geom.geomYDotplot].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param binWidth When method is "dotdensity", this specifies maximum bin width.
 *  When method is "histodot", this specifies bin width.
 * @param stackSize The diameter of the dots relative to binWidth.
 * @param stroke Width of the dot border.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 */
interface YDotplotAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val binWidth: Any?
    val stackSize: Any?
    val stroke: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "binwidth" to binWidth,
        "stacksize" to stackSize,
        "stroke" to stroke,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill
    )
}