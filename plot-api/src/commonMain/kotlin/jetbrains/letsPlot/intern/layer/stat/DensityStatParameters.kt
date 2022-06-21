/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options

interface DensityStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val bw: Any?       // ToDo: constants (bin width: string (method) or Number)
    val kernel: String?   // ToDo: constants
    val n: Int?
    val trim: Boolean?
    val adjust: Number?
    val fullScanMax: Int?

    override fun seal() = Options.of(
        "bw" to bw,
        "kernel" to kernel,
        "n" to n,
        "trim" to trim,
        "adjust" to adjust,
        "fs_max" to fullScanMax
    )
}
