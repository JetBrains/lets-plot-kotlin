package com.jetbrains.datalore.plot.layer.geom

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.OptionsCapsule

interface BarAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val width: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "width" to width,
        "size" to size
    )
}