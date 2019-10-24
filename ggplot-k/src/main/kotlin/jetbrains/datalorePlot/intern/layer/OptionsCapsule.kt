package jetbrains.datalorePlot.intern.layer

import jetbrains.datalorePlot.intern.Options

interface OptionsCapsule {
    fun seal(): Options
}

