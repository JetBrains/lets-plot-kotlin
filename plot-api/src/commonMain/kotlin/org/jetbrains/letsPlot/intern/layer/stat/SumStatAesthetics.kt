package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statSum()][org.jetbrains.letsPlot.stat.statSum].
 *
 * @property x X-axis coordinates.
 * @property y Y-axis coordinates.
 */
interface SumStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?

    override fun seal(): Options {
        return Options.of(
            "x" to x,
            "y" to y,
        )
    }
}