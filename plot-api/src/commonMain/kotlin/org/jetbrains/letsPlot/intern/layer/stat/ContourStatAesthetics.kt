/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statContour()][org.jetbrains.letsPlot.stat.statContour].
 *
 * @property x X-axis coordinates of the center of rectangles, forming a tessellation.
 * @property y Y-axis coordinates of the center of rectangles, forming a tessellation.
 * @property z Value at point (x, y).
 */
interface ContourStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val z: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "z" to z
    )
}