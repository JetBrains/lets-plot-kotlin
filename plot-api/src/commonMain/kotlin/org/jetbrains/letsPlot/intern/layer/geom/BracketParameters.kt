package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [geomBracket()][org.jetbrains.letsPlot.geom.geomBracket].
 *
 * @param bracketShorten default = 0.
 *  Symmetrically shorten the bracket by shifting both ends toward the center.
 *  Expects values between 0 and 1, where 0 corresponds to no shortening and 1 to a fully collapsed bracket.
 * @param tipLengthUnit default = "size" ("res", "identity", "size", "px").
 *  Unit for the `lenStart` and `lenEnd` aesthetics.
 *  Possible values:
 *
 *  - "res": the unit equals the smallest distance between data points along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with `size = 1`;
 *  - "px": the unit is measured in screen pixels.
 */
interface BracketParameters : OptionsCapsule {
    val bracketShorten: Number?
    val tipLengthUnit: String?

    override fun seal() = Options.of(
        Option.Geom.Bracket.BRACKET_SHORTEN to bracketShorten,
        Option.Geom.Bracket.TIPLENGTH_UNIT to tipLengthUnit
    )
}