package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic mappings supported by [statSummaryBin()][org.jetbrains.letsPlot.stat.statSummaryBin].
 *
 * @param x X-axis coordinates for vertical interval / position of mid-point for horizontal interval.
 * @param y Y-axis coordinates for horizontal interval / position of mid-point for vertical interval.
 */
class SummaryBinStatMapping(
    val x: Any? = null,
    val y: Any? = null
) : OptionsCapsule {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y
    )
}