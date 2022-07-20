/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BoxplotAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val lower: Any?
    val middle: Any?
    val upper: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
    val linetype: Any?
    val shape: Any?
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
            "alpha" to alpha,
            "color" to color,
            "fill" to fill,
            "size" to size,
            "linetype" to linetype,
            "shape" to shape,
            "width" to width
        )
    }
}
