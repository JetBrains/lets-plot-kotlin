/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface Count2dStatAesthetics : OptionsCapsule {
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