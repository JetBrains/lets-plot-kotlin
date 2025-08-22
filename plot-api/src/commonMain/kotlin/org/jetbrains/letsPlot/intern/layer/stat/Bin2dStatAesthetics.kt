/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statBin2D()][org.jetbrains.letsPlot.stat.statBin2D].
 *
 * ## Notes
 * The bin2d stat requires x,y (weight is optional)
 *
 * @property x X-axis value.
 * @property y Y-axis value.
 * @property weight Used by `Stat.bin2D()`stat to compute weighted sum instead of simple count.
 */
interface Bin2dStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "weight" to weight
    )
}