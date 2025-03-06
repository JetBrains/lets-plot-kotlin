package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option.Geom.Tile
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface WithHeightUnitOption : OptionsCapsule {
    val heightUnit: String?

    override fun seal() = Options.of(
        Tile.HEIGHT_UNIT to heightUnit
    )
}