package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options

interface SummaryBinStatParameters : BinStatParameters {
    val fn: String?
    val fnMin: String?
    val fnMax: String?
    val quantiles: List<Number>?

    override fun seal() = super.seal() + Options.of(
        Stat.Summary.FUN to fn,
        Stat.Summary.FUN_MIN to fnMin,
        Stat.Summary.FUN_MAX to fnMax,
        Stat.Summary.QUANTILES to quantiles
    )
}