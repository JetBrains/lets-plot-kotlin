/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statBin()][org.jetbrains.letsPlot.stat.statBin].
 *
 * @property bins Number of bins. Overridden by `binWidth`.
 * @property binWidth The width of the bins. The default is to use bin widths that cover
 *  the entire range of the data. You should always override this value, exploring multiple widths
 *  to find the best to illustrate the stories in your data.
 * @property center Specifies x-value to align bin centers to.
 * @property boundary Specifies x-value to align bin boundary (i.e., point between bins) to.
 * @property threshold Only bins with a `..count..` greater than the threshold will be displayed.
 *  This is useful for free scales in facets - use threshold=0 to make the plot take up the entire panel space.
 */
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
