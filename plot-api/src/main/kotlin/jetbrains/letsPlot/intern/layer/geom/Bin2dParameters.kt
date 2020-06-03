/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface Bin2dParameters : OptionsCapsule {
    val bins: Any?
    val binwidth: Any?
    val drop: Any?

    override fun seal() = Options.of(
        "bins" to bins,
        "binwidth" to binwidth,
        "drop" to drop
    )
}