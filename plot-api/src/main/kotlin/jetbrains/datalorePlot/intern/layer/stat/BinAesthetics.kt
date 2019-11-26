package jetbrains.datalorePlot.intern.layer.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule

interface BinAesthetics : OptionsCapsule {
    val x: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "weight" to weight,
        "x" to x
    )
}