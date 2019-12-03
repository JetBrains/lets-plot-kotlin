/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

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
