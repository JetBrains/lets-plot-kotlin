/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.config.Option.Stat
import jetbrains.letsPlot.intern.Options

interface Bin2dStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val bins: Pair<Int, Int>?
    val binWidth: Pair<Number?, Number?>?
    val drop: Boolean?

    override fun seal() = Options.of(
        Stat.Bin2d.BINS to bins?.toList(),
        Stat.Bin2d.BINWIDTH to binWidth?.toList(),
        Stat.Bin2d.DROP to drop
    )
}