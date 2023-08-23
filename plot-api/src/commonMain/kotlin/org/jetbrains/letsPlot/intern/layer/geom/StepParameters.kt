package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface StepParameters : OptionsCapsule {
    val direction: String?
    val pad: Boolean?

    override fun seal() = Options.of(
        Option.Geom.Step.DIRECTION to direction,
        Option.Geom.Step.PADDED to pad
    )
}