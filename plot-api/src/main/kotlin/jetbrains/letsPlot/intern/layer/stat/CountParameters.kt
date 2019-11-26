package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface CountParameters : OptionsCapsule {
    // no parameters
    override fun seal() = Options.empty()
}
