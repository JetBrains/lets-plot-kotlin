package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface CurveAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val xend: Any?
    val yend: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?
    val sizeStart: Any?
    val sizeEnd: Any?
    val strokeStart: Any?
    val strokeEnd: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "xend" to xend,
        "yend" to yend,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size,
        "size_start" to sizeStart,
        "size_end" to sizeEnd,
        "stroke_start" to strokeStart,
        "stroke_end" to strokeEnd
    )
}