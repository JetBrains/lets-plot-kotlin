package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option.Geom.Tile
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @property widthUnit Unit for width values.
 *  Possible values:
 *  - "res": the unit equals the smallest distance between adjacent values along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with size=1;
 *  - "px": the unit is measured in screen pixels.
 */
interface WithWidthUnitOption : OptionsCapsule {
    val widthUnit: String?

    override fun seal() = Options.of(
        Tile.WIDTH_UNIT to widthUnit
    )
}