package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statBinHex()][org.jetbrains.letsPlot.stat.statBinHex].
 *
 * ## Notes
 * The binhex stat requires x,y (weight is optional)
 *
 * @property x X-axis value.
 * @property y Y-axis value.
 * @property weight Used to compute weighted sum instead of simple count.
 */
interface BinHexStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "weight" to weight
    )
}