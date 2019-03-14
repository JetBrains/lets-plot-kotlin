package com.jetbrains.datalore.plot.layer.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.FreezableOptions

interface DensityParameters : FreezableOptions {
    val bw: Any?
    val kernel: Any?
    val n: Any?
    val trim: Any?

    override fun toFrozen() = Options.of(
        "bw" to bw,
        "kernel" to kernel,
        "n" to n,
        "trim" to trim
    )
}
