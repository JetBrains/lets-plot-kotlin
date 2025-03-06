package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

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