/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statECDF()][org.jetbrains.letsPlot.stat.statECDF].
 *
 * @property x X-axis coordinates.
 * @property y Y-axis coordinates.
 */
interface ECDFStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y
    )
}