/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BinStatParameters : OptionsCapsule {
    val bins: Int?
    val binWidth: Number?
    val center: Number?
    val boundary: Number?
    val threshold: Number?

    override fun seal() = Options.of(
        Option.Stat.Bin.BINS to bins,
        Option.Stat.Bin.BINWIDTH to binWidth,
        Option.Stat.Bin.CENTER to center,
        Option.Stat.Bin.BOUNDARY to boundary,
        Option.Stat.Bin.THRESHOLD to threshold
    )
}
