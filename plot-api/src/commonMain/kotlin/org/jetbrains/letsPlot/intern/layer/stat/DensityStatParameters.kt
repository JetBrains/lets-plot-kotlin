/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statDensity()][org.jetbrains.letsPlot.stat.statDensity].
 *
 * @property bw The method (or exact value) of bandwidth. Can be String ("nrd0" or "nrd") or Double.
 * @property kernel The kernel used to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *  "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic").
 * @property n The number of sampled points for plotting the function.
 * @property trim If false, each density is computed on the full range of the data.
 *  If true, each density is computed over the range of that group.
 * @property adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @property fullScanMax Maximum size of data to use density computation with 'full scan'.
 *  For bigger data, less accurate but more efficient density computation is applied.
 * @property quantiles Calculates given quantiles of the density estimate.
 */
interface DensityStatParameters : OptionsCapsule {
    val bw: Any?       // ToDo: constants (bin width: string (method) or Number)
    val kernel: String?   // ToDo: constants
    val n: Int?
    val trim: Boolean?
    val adjust: Number?
    val fullScanMax: Int?
    val quantiles: List<Number>?

    override fun seal() = Options.of(
        "bw" to bw,
        "kernel" to kernel,
        "n" to n,
        "trim" to trim,
        "adjust" to adjust,
        "fs_max" to fullScanMax,
        "quantiles" to quantiles
    )
}
