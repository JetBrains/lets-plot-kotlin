/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface Density2dStatParameters : OptionsCapsule {
    val bw: Any?       // ToDo: constants. (bin width: string (method) or Number or Pair<Number>)
    val kernel: String?   // ToDo: constants
    val n: Int?
    val adjust: Number?
    val contour: Boolean?  // If TRUE, contour the results of the 2d density estimation.
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