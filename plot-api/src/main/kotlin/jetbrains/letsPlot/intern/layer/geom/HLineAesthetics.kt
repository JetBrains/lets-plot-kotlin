package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface HLineAesthetics : OptionsCapsule {
    val yintercept: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "yintercept" to yintercept,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}