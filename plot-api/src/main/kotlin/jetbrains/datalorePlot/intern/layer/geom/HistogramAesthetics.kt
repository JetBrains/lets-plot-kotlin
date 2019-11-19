package jetbrains.datalorePlot.intern.layer.geom

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule
import jetbrains.datalorePlot.intern.layer.geom.AreaAesthetics

interface HistogramAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
//    val weight: Any?  moved to `bin` stat

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "size" to size
    )
}