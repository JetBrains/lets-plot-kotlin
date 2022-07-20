/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface RectAesthetics : OptionsCapsule {
    val xmin: Any?
    val xmax: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "xmin" to xmin,
        "xmax" to xmax,
        "ymin" to ymin,
        "ymax" to ymax,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size,
        "fill" to fill
    )
}