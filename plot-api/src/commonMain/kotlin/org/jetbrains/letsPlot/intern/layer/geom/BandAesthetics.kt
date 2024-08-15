package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BandAesthetics : OptionsCapsule {
    val xmin: Any?
    val xmax: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
    val linetype: Any?

    override fun seal() = Options.of(
        "xmin" to xmin,
        "xmax" to xmax,
        "ymin" to ymin,
        "ymax" to ymax,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "size" to size,
        "linetype" to linetype
    )
}