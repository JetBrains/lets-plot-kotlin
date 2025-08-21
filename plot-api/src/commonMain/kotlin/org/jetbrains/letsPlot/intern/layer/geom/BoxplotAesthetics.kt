/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BoxplotAesthetics : OptionsCapsule {
    /** x-axis coordinate for vertical boxplot. */
    val x: Any?
    /** y-axis coordinate for horizontal boxplot. */
    val y: Any?
    /** Lower hinge, 25% quantile for vertical boxplot. */
    val lower: Any?
    /** Median, 50% quantile for vertical boxplot. */
    val middle: Any?
    /** Upper hinge, 75% quantile for vertical boxplot. */
    val upper: Any?
    /** Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for vertical boxplot. */
    val ymin: Any?
    /** Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for vertical boxplot. */
    val ymax: Any?
    /** Lower hinge, 25% quantile for horizontal boxplot. */
    val xlower: Any?
    /** Median, 50% quantile for horizontal boxplot. */
    val xmiddle: Any?
    /** Upper hinge, 75% quantile for horizontal boxplot. */
    val xupper: Any?
    /** Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for horizontal boxplot. */
    val xmin: Any?
    /** Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for horizontal boxplot. */
    val xmax: Any?
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    val alpha: Any?
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val color: Any?
    /** Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val fill: Any?
    /** Lines width. */
    val size: Any?
    /** Type of the line of border. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types). */
    val linetype: Any?
    /** Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes). */
    val shape: Any?
    /** Rotation angle of the shape, in degrees. */
    val angle: Any?
    /** Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes. */
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
