package com.jetbrains.datalore.plot.layer.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.OptionsCapsule

interface CountParameters : OptionsCapsule {
    // no parameters
    override fun seal() = Options.empty()
}
