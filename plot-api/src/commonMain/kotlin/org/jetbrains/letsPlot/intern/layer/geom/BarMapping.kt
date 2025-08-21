/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics

/**
 * Set of aesthetic mappings for bar.
 * Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class BarMapping(
    /** X-axis value (this value will produce cases or bins for bars). */
    override var x: Any? = null,
    /** Y-axis value (this value will be used to multiply the case's or bin's counts). */
    override var y: Any? = null,
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    override var alpha: Any? = null,
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var color: Any? = null,
    /** Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var fill: Any? = null,
    /** Width of a bar. */
    override var width: Any? = null,
    /** Defines bar line width. */
    override var size: Any? = null,
    /** Aesthetic group. */
    override var group: Any? = null,
    /** Used by `Stat.count()` stat to compute weighted sum instead of simple count. */
    override var weight: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_a: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_b: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_c: Any? = null
) : BarAesthetics, CountStatAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<BarAesthetics>.seal() +
            super<CountStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


