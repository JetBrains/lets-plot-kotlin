package jetbrains.datalorePlot.intern.layer.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule

interface BinAesthetics : OptionsCapsule {
    val weight: Any?

    override fun seal() = Options.of(
        "weight" to weight
    )
}