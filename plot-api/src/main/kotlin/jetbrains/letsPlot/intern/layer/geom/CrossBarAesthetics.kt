/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface CrossBarAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val middle: Any?
    val width: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val shape: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "middle" to middle,
        "width" to width,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "shape" to shape,
        "size" to size
    )
}