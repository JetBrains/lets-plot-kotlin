package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface CountAesthetics : OptionsCapsule {
    val weight: Any?

    override fun seal(): Options {
        return Options.of("weight" to weight)
    }
}