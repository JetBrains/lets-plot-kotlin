package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options

/**
 * Properties for parameters of [statSummaryBin()][org.jetbrains.letsPlot.stat.statSummaryBin].
 *
 * @property fn Name of function computing stat variable `..y..`.
 * @property fnMin Name of function computing stat variable `..ymin..`.
 * @property fnMax Name of function computing stat variable `..ymax..`.
 * @property quantiles A list of probabilities defining the quantile functions "lq", "mq" and "uq". Must contain exactly 3 values between 0 and 1.
 */
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