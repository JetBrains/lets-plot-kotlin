package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface SinaParameters : OptionsCapsule {
    val showHalf: Number?
    val seed: Int?

    override fun seal() = Options.of(
        Option.Geom.Sina.SHOW_HALF to showHalf,
        Option.Geom.Sina.SEED to seed
    )
}