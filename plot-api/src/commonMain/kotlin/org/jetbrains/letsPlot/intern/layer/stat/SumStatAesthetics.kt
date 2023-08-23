package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

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