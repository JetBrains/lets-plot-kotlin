/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.base.stat.Bin2dStat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface Bin2dParameters : OptionsCapsule {
    val binCount: List<Int>?
    val binWidth: List<Double?>?
    val drop: Boolean?

    override fun seal() = Options.of(
        Bin2dStat.P_BINS to binCount,
        Bin2dStat.P_BINWIDTH to binWidth,
        Bin2dStat.P_DROP to drop
    )
}