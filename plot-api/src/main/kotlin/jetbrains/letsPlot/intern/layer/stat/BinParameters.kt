/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface BinParameters : OptionsCapsule {
    val binCount: Int
    val binWidth: Double?
    val center: Double?
    val boundary: Double?

    override fun seal() = Options.of(
        "bins" to binCount,
        "binwidth" to binWidth,
        "center" to center,
        "boundary" to boundary
    )

    companion object {
        const val DEF_BIN_COUNT = 30
    }
}
