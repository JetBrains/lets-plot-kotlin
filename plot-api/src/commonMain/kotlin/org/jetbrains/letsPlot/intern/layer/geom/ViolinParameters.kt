package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomViolin()][org.jetbrains.letsPlot.geom.geomViolin].
 *
 * @param showHalf If -1 then it's drawing only half of each violin. If 1 then it's drawing other half. If 0 then violins looking as usual.
 * @param quantileLines Show the quantile lines.
 */
interface ViolinParameters : OptionsCapsule {
    val showHalf: Number?
    val quantileLines: Boolean?

    override fun seal() = Options.of(
        Option.Geom.Violin.SHOW_HALF to showHalf,
        Option.Geom.Violin.QUANTILE_LINES to quantileLines,
    )
}