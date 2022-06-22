/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.OptionsCapsule

/**
 * The "Five Number Summary" requires x,y (weight is optional)
 */
interface BoxplotStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val weight: Any?

    override fun seal(): Options {
        return Options.of(
            "x" to x,
            "y" to y,
            "weight" to weight
        )
    }
}