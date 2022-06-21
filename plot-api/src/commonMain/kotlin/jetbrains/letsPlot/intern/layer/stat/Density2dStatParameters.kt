/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options

interface Density2dStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val bw: Any?
    val kernel: String?
    val n: Int?           // ToDo: just Int or a pair of ints?
    val adjust: Number?
    val contour: Boolean?
    val bins: Int?
    val binWidth: Number?

    override fun seal() = Options.of(
        "bw" to bw,
        "kernel" to kernel,
        "n" to n,
        "adjust" to adjust,
        "contour" to contour,
        "bins" to bins,
        "binwidth" to binWidth
    )
}