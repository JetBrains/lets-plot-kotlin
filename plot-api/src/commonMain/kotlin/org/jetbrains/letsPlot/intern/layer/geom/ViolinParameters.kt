package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface ViolinParameters : OptionsCapsule {
    val showHalf: Number?
    val quantileLines: Boolean?

    override fun seal() = Options.of(
        Option.Geom.Violin.SHOW_HALF to showHalf,
        Option.Geom.Violin.QUANTILE_LINES to quantileLines,
    )
}