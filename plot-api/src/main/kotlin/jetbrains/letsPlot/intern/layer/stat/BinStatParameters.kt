/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface BinStatParameters : OptionsCapsule {
    val bins: Int
    val binWidth: Number?
    val center: Number?
    val boundary: Number?

    override fun seal() = Options.of(
        "bins" to bins,
        "binwidth" to binWidth,
        "center" to center,
        "boundary" to boundary
    )

    companion object {
        const val DEF_BIN_COUNT = 30
    }
}
