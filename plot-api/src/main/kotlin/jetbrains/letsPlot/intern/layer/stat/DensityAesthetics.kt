package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.geom.AreaAesthetics

interface DensityAesthetics : AreaAesthetics {
    val weight: Any?

    override fun seal(): Options {
        return super.seal() +
                Options.of("weight" to weight)
    }
}