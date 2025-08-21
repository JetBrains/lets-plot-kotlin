/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BarAesthetics : OptionsCapsule {
    /** X-axis value (this value will produce cases or bins for bars). */
    val x: Any?
    /** Y-axis value (this value will be used to multiply the case's or bin's counts). */
    val y: Any?
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    val alpha: Any?
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val color: Any?
    /** Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val fill: Any?
    /** Width of a bar. */
    val width: Any?
    /** Defines bar line width. */
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "width" to width,
        "size" to size
    )
}