/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statCount()][org.jetbrains.letsPlot.stat.statCount].
 *
 * @property x X-axis value (this value will produce cases or bins for bars).
 * @property weight Used by the stat to compute weighted sum instead of simple count.
 */
interface CountStatAesthetics : OptionsCapsule {
    val x: Any?
    val weight: Any?

    override fun seal(): Options {
        return Options.of(
            "x" to x,
            "weight" to weight
        )
    }
}