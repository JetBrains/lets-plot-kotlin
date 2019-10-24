package jetbrains.datalorePlot.intern.layer.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule

interface DensityParameters : OptionsCapsule {
    val bw: Any?
    val kernel: Any?
    val n: Any?
    val trim: Any?

    override fun seal() = Options.of(
        "bw" to bw,
        "kernel" to kernel,
        "n" to n,
        "trim" to trim
    )
}
