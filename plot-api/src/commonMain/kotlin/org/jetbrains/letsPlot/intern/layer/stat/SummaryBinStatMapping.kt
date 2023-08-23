package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

class SummaryBinStatMapping(
    val x: Any? = null,
    val y: Any? = null
) : OptionsCapsule {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y
    )
}