package com.jetbrains.datalore.plot.layer.geom

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.OptionsCapsule
import com.jetbrains.datalore.plot.layer.WithGroupOption

interface AreaAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size
    )
}

class AreaMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null
) : AreaAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


