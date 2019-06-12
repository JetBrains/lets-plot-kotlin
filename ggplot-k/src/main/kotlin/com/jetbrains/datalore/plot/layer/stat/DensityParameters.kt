package com.jetbrains.datalore.plot.layer.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.OptionsCapsule

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
