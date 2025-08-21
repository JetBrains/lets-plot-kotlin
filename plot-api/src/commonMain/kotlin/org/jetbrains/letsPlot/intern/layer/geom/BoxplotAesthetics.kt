/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomBoxplot()][org.jetbrains.letsPlot.geom.geomBoxplot].
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
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Lines width.
 * @param linetype Type of the line of border.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param shape Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param angle Rotation angle of the shape, in degrees.
 * @param width Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes.
 */
interface BoxplotAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val lower: Any?
    val middle: Any?
    val upper: Any?
    val ymin: Any?
    val ymax: Any?
    val xlower: Any?
    val xmiddle: Any?
    val xupper: Any?
    val xmin: Any?
    val xmax: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
    val linetype: Any?
    val shape: Any?
    val angle: Any?
    val width: Any?

    override fun seal(): Options {
        return Options.of(
            "x" to x,
            "y" to y,
            "lower" to lower,
            "middle" to middle,
            "upper" to upper,
            "ymin" to ymin,
            "ymax" to ymax,
            "xlower" to xlower,
            "xmiddle" to xmiddle,
            "xupper" to xupper,
            "xmin" to xmin,
            "xmax" to xmax,
            "alpha" to alpha,
            "color" to color,
            "fill" to fill,
            "size" to size,
            "linetype" to linetype,
            "shape" to shape,
            "angle" to angle,
            "width" to width
        )
    }
}
