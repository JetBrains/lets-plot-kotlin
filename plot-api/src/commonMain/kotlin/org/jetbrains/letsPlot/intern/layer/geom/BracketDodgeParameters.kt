package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options

/**
 * Properties for parameters of [geomBracketDodge()][org.jetbrains.letsPlot.geom.geomBracketDodge].
 *
 * @param bracketShorten default = 0.
 *  Symmetrically shorten the bracket by shifting both ends toward the center.
 *  Expects values between 0 and 1, where 0 corresponds to no shortening and 1 to a fully collapsed bracket.
 * @param tipLengthUnit default = "size" ("res", "identity", "size", "px").
 *  Unit for the `lenstart` and `lenend` aesthetics.
 *  Possible values:
 *
 *  - "res": the unit equals the smallest distance between data points along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with `size = 1`;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param dodgeWidth default = 0.95.
 *  Width used to compute bracket positions.
 *  Expected to match the dodge width used by other layers for proper alignment.
 * @param nGroup Total number of dodged groups per category; used to interpret `istart`/`iend` indices.
 *  By default, this value is inferred from the data when possible, but can be set explicitly if needed.
 */
interface BracketDodgeParameters : BracketParameters {
    val dodgeWidth: Number?
    val nGroup: Int?

    override fun seal() = super.seal() + Options.of(
        Option.Geom.BracketDodge.DODGE_WIDTH to dodgeWidth,
        Option.Geom.BracketDodge.NGROUP to nGroup
    )
}