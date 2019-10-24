package jetbrains.datalorePlot.intern.layer.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule

interface CountParameters : OptionsCapsule {
    // no parameters
    override fun seal() = Options.empty()
}
