/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.Count2dStatAesthetics

/**
 * Aesthetic mappings supported by [geomPie()][org.jetbrains.letsPlot.geom.geomPie].
 *
 * @param x X-axis center coordinate.
 * @param y Y-axis center coordinate.
 * @param slice Value that determines the size of each pie slice.
 * @param explode Distance to explode pie slice from center.
 * @param size Overall pie size.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Pie slice border color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param stroke Pie slice border width.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param fill Pie slice fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param weight Used by stat to compute values for pie slices.
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class PieMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var slice: Any? = null,
    override var explode: Any? = null,
    override var size: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var stroke: Any? = null,
    override var group: Any? = null,
    override var weight: Any? = null,
    override var fill: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : PieAesthetics, Count2dStatAesthetics, WithGroupOption, PaintAesthetics {

    override fun seal() = super<PieAesthetics>.seal() +
            super<Count2dStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}