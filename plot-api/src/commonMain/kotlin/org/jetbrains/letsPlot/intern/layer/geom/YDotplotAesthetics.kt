/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomYDotplot()][org.jetbrains.letsPlot.geom.geomYDotplot].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param binWidth Width of bins for histogram-like binning.
 * @param stackSize Stacking height size for dots.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Size.
 * @param stroke Stroke width.
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