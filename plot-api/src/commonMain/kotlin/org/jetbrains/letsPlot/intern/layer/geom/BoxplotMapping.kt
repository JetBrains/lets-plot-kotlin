/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics

/**
 * Aesthetic mappings supported by [geomBoxplot()][org.jetbrains.letsPlot.geom.geomBoxplot].
 *
 * @param x x-axis coordinate for vertical boxplot.
 * @param y y-axis coordinate for horizontal boxplot.
 * @param lower Lower hinge, 25% quantile for vertical boxplot.
 * @param middle Median, 50% quantile for vertical boxplot.
 * @param upper Upper hinge, 75% quantile for vertical boxplot.
 * @param ymin Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for vertical boxplot.
 * @param ymax Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for vertical boxplot.
 * @param xlower Lower hinge, 25% quantile for horizontal boxplot.
 * @param xmiddle Median, 50% quantile for horizontal boxplot.
 * @param xupper Upper hinge, 75% quantile for horizontal boxplot.
 * @param xmin Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for horizontal boxplot.
 * @param xmax Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for horizontal boxplot.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Border color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Border width.
 * @param linetype Type of the line of border.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param shape Point shape for outliers.
 *  For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param angle Rotation angle of the shape for outliers, in degrees.
 * @param width Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param weight Statistical weight for observation.
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class BoxplotMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var lower: Any? = null,
    override var middle: Any? = null,
    override var upper: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var xlower: Any? = null,
    override var xmiddle: Any? = null,
    override var xupper: Any? = null,
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var shape: Any? = null,
    override var angle: Any? = null,
    override var width: Any? = null,
    override var group: Any? = null,
    override var weight: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : BoxplotAesthetics, BoxplotStatAesthetics, WithGroupOption, PaintAesthetics {

    override fun seal() = super<BoxplotAesthetics>.seal() +
            super<BoxplotStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}
