/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface RibbonAesthetics : OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "size" to size,
        "linetype" to linetype,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha
    )
}