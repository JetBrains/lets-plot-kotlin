/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomSina()][org.jetbrains.letsPlot.geom.geomSina].
 *
 * @param showHalf Control which half of the sina plot to show (-1 = left, 0 = both, 1 = right).
 * @param seed Random seed for sina point positioning reproducibility.
 */
interface SinaParameters : OptionsCapsule {
    val showHalf: Number?
    val seed: Int?

    override fun seal() = Options.of(
        Option.Geom.Sina.SHOW_HALF to showHalf,
        Option.Geom.Sina.SEED to seed
    )
}