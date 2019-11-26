package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Options

interface OptionsCapsule {
    fun seal(): Options
}

