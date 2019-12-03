package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface DensityParameters : OptionsCapsule {
    val bw: Any?
    val kernel: Any?
    val n: Any?
    val trim: Any?
    val adjust: Any?

    override fun seal() = Options.of(
        "bw" to bw,
        "kernel" to kernel,
        "n" to n,
        "trim" to trim,
        "adjust" to adjust
    )
}
