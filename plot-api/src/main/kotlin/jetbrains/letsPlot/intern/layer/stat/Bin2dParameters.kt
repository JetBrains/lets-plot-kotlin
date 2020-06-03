/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface Bin2dParameters : OptionsCapsule {
    val bins: List<Int>?
    val binwidth: List<Double?>?
    val drop: Boolean?

    override fun seal() = Options.of(
        P_BINS to bins,
        P_BINWIDTH to binwidth,
        P_DROP to drop
    )

    companion object {
        const val P_BINS = "bins"
        const val P_BINWIDTH = "binwidth"
        const val P_DROP = "drop"
    }
}