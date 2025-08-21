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
 *
 * @param x X-axis value (this value will produce cases or bins for bars).
 * @param y Y-axis value (this value will be used to multiply the case's or bin's counts).
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param width Width of a bar.
 * @param size Defines bar line width.
 * @param weight Used by `Stat.count()` stat to compute weighted sum instead of simple count.
 */
class BarMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var width: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var weight: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : BarAesthetics, CountStatAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<BarAesthetics>.seal() +
            super<CountStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


