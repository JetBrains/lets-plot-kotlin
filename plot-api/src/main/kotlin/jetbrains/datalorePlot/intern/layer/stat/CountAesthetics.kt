package jetbrains.datalorePlot.intern.layer.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule
import jetbrains.datalorePlot.intern.layer.geom.BarAesthetics

interface CountAesthetics : OptionsCapsule {
    val weight: Any?

    override fun seal(): Options {
        return Options.of("weight" to weight)
    }
}