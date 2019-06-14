package com.jetbrains.datalore.plot.layer.geom

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.OptionsCapsule

interface TileAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val width: Any?
    val height: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "width" to width,
        "height" to height,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size
    )
}