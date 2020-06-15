/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface Bin2dAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val weight: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "weight" to weight,
        "fill" to fill,
        "x" to x,
        "y" to y
    )
}