package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option.Geom.Tile
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @property widthUnit Unit for width values.
 */
interface WithWidthUnitOption : OptionsCapsule {
    val widthUnit: String?

    override fun seal() = Options.of(
        Tile.WIDTH_UNIT to widthUnit
    )
}