package com.jetbrains.datalore.plot

import com.jetbrains.datalore.plot.layer.OptionsCapsule
import com.jetbrains.datalore.plot.layer.WithGroupOption

class GenericAesMapping(
    var x: Any? = null,
    var y: Any? = null,
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    override var group: Any? = null
) : OptionsCapsule, WithGroupOption {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill
    ) + group()
}