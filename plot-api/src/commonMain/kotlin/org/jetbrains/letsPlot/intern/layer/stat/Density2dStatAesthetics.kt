/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statDensity2D()][org.jetbrains.letsPlot.stat.statDensity2D].
 *
 * @property x X-axis coordinates of the center of rectangles, forming a tessellation.
 * @property y Y-axis coordinates of the center of rectangles, forming a tessellation.
 * @property weight Used by the stat to compute weighted density.
 */
interface Density2dStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "weight" to weight
    )
}