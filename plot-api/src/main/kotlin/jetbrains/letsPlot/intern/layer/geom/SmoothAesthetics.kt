/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface SmoothAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val ymin: Any?
    val ymax: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "ymin" to ymin,
        "ymax" to ymax,
        "size" to size,
        "linetype" to linetype,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha
    )
}