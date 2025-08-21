/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomYDotplot()][org.jetbrains.letsPlot.geom.geomYDotplot].
 *
 * @property x X-axis coordinates.
 * @property y Y-axis coordinates.
 * @property binWidth When method is "dotdensity", this specifies maximum bin width.
 *  When method is "histodot", this specifies bin width.
 * @property stackSize The diameter of the dots relative to binWidth.
 * @property stroke Width of the dot border.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property fill Fill color.
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