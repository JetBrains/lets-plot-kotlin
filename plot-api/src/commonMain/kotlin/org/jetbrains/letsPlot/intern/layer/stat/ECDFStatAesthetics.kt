package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface ECDFStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y
    )
}