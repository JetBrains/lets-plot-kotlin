/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics

/**
 * Aesthetic mappings supported by [geomBar()][org.jetbrains.letsPlot.geom.geomBar].
 *
 * @param x X-axis value (this value will produce cases or bins for bars).
 * @param y Y-axis value (this value will be used to multiply the case's or bin's counts).
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Bar border color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Bar fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param width Bar width.
 * @param size Bar border width.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, fill).
 * @param weight Used by `Stat.count()` stat to compute weighted sum instead of simple count.
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
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


