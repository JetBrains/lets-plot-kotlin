/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @param x X-axis value.
 * @param binWidth Width of bins for histogram-like binning.
 * @param stackSize Stacking height size for dots.
 * @param stroke Stroke width.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class DotplotMapping(
    override var x: Any? = null,
    override var binWidth: Any? = null,
    override var stackSize: Any? = null,
    override var stroke: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : DotplotAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<DotplotAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}