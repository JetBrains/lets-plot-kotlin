/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics

/**
 * Set of aesthetic mappings for boxplot.
 * Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class BoxplotMapping(
    /** x-axis coordinate for vertical boxplot. */
    override var x: Any? = null,
    /** y-axis coordinate for horizontal boxplot. */
    override var y: Any? = null,
    /** Lower hinge, 25% quantile for vertical boxplot. */
    override var lower: Any? = null,
    /** Median, 50% quantile for vertical boxplot. */
    override var middle: Any? = null,
    /** Upper hinge, 75% quantile for vertical boxplot. */
    override var upper: Any? = null,
    /** Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for vertical boxplot. */
    override var ymin: Any? = null,
    /** Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for vertical boxplot. */
    override var ymax: Any? = null,
    /** Lower hinge, 25% quantile for horizontal boxplot. */
    override var xlower: Any? = null,
    /** Median, 50% quantile for horizontal boxplot. */
    override var xmiddle: Any? = null,
    /** Upper hinge, 75% quantile for horizontal boxplot. */
    override var xupper: Any? = null,
    /** Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for horizontal boxplot. */
    override var xmin: Any? = null,
    /** Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for horizontal boxplot. */
    override var xmax: Any? = null,
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    override var alpha: Any? = null,
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var color: Any? = null,
    /** Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var fill: Any? = null,
    /** Lines width. */
    override var size: Any? = null,
    /** Type of the line of border. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types). */
    override var linetype: Any? = null,
    /** Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes). */
    override var shape: Any? = null,
    /** Rotation angle of the shape, in degrees. */
    override var angle: Any? = null,
    /** Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes. */
    override var width: Any? = null,
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
) : BoxplotAesthetics, BoxplotStatAesthetics, WithGroupOption, PaintAesthetics {

    override fun seal() = super<BoxplotAesthetics>.seal() +
            super<BoxplotStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}
