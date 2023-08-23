package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface ECDFStatParameters : OptionsCapsule {
    val n: Int?
    val pad: Boolean?

    override fun seal() = Options.of(
        Option.Stat.ECDF.N to n,
        Option.Stat.ECDF.PADDED to pad
    )
}