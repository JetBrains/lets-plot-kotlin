/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface PointRangeAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val shape: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "ymin" to ymin,
        "ymax" to ymax,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "shape" to shape,
        "size" to size
    )
}