package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options

/**
 * TODO
 */
interface BracketDodgeParameters : BracketParameters {
    val dodgeWidth: Number?
    val nGroup: Int?

    override fun seal() = Options.of(
        Option.Geom.BracketDodge.DODGE_WIDTH to dodgeWidth,
        Option.Geom.BracketDodge.NGROUP to nGroup
    )
}