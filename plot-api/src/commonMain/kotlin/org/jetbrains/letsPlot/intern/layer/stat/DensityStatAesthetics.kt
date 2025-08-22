/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statDensity()][org.jetbrains.letsPlot.stat.statDensity].
 *
 * @property x X-axis coordinates.
 * @property weight Used by the stat to compute weighted density.
 */
interface DensityStatAesthetics : OptionsCapsule {
    val x: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "x" to x,
        "weight" to weight
    )
}