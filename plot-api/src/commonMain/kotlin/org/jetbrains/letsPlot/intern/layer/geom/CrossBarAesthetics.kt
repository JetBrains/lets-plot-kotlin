/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface CrossBarAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val y: Any?
    val xmin: Any?
    val xmax: Any?
    val width: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "y" to y,
        "xmin" to xmin,
        "xmax" to xmax,
        "width" to width,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size
    )
}