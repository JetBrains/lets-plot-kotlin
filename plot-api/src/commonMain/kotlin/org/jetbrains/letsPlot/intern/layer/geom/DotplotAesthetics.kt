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
 * Aesthetic parameters supported by [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @param x X-axis value.
 * @param binWidth Width of bins for histogram-like binning.
 * @param stackSize Stacking height size for dots.
 * @param stroke Dot outline width.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Dot outline color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Dot fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Dot size.
 */
interface DotplotAesthetics : OptionsCapsule {
    val x: Any?
    val binWidth: Any?
    val stackSize: Any?
    val stroke: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "x" to x,
        "binwidth" to binWidth,
        "stacksize" to stackSize,
        "stroke" to stroke,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill
    )
}