package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options

interface OptionsCapsule {
    fun seal(): Options
}

