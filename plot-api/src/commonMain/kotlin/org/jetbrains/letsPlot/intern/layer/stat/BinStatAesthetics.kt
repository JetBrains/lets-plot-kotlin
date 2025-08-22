/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statBin()][org.jetbrains.letsPlot.stat.statBin].
 *
 * @property x X-axis coordinates.
 * @property weight Used to compute weighted sum instead of simple count.
 */
interface BinStatAesthetics : OptionsCapsule {
    val x: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "weight" to weight,
        "x" to x
    )
}