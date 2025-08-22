/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statBin2D()][org.jetbrains.letsPlot.stat.statBin2D].
 *
 * @property bins Number of bins in both directions, vertical and horizontal. Overridden by `binWidth`.
 * @property binWidth The width of the bins in both directions, vertical and horizontal. Overrides `bins`.
 *  The default is to use bin widths that cover the entire range of the data.
 * @property drop Specifies whether to remove all bins with 0 counts.
 */
interface Bin2dStatParameters : OptionsCapsule {
    val bins: Pair<Int, Int>?
    val binWidth: Pair<Number?, Number?>?
    val drop: Boolean?

    override fun seal() = Options.of(
        Stat.Bin2d.BINS to bins?.toList(),
        Stat.Bin2d.BINWIDTH to binWidth?.toList(),
        Stat.Bin2d.DROP to drop
    )
}