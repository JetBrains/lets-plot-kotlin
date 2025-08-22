package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statBinHex()][org.jetbrains.letsPlot.stat.statBinHex].
 *
 * @property bins Number of bins in both directions, vertical and horizontal. Overridden by `binWidth`.
 * @property binWidth The width of the bins in both directions, vertical and horizontal. Overrides `bins`.
 *  The default is to use bin widths that cover the entire range of the data.
 * @property drop Specifies whether to remove all bins with 0 counts.
 */
interface BinHexStatParameters : OptionsCapsule {
    val bins: Pair<Int, Int>?
    val binWidth: Pair<Number?, Number?>?
    val drop: Boolean?

    override fun seal() = Options.of(
        Stat.BinHex.BINS to bins?.toList(),
        Stat.BinHex.BINWIDTH to binWidth?.toList(),
        Stat.BinHex.DROP to drop
    )
}