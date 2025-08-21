package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomSina()][org.jetbrains.letsPlot.geom.geomSina].
 *
 * @property showHalf If -1, only half of each group is drawn. If 1, another half is drawn. If 0, sina look as usual.
 * @property seed A random seed to make the jitter reproducible.
 */
interface SinaParameters : OptionsCapsule {
    val showHalf: Number?
    val seed: Int?

    override fun seal() = Options.of(
        Option.Geom.Sina.SHOW_HALF to showHalf,
        Option.Geom.Sina.SEED to seed
    )
}