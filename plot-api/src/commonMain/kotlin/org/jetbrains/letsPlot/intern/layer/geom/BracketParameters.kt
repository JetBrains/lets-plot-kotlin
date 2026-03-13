package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * TODO
 */
interface BracketParameters : OptionsCapsule {
    val bracketShorten: Number?
    val tipLengthUnit: String?

    override fun seal() = Options.of(
        Option.Geom.Bracket.BRACKET_SHORTEN to bracketShorten,
        Option.Geom.Bracket.TIPLENGTH_UNIT to tipLengthUnit
    )
}