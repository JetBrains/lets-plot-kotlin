/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statDensity2D()][org.jetbrains.letsPlot.stat.statDensity2D].
 *
 * @property bw The method (or exact value) of bandwidth. Either a String (choose among "nrd0" and "nrd"),
 *  or a Double array of length 2.
 * @property kernel The kernel used to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *  "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic").
 * @property n The number of sampled points for plotting the function (on x and y direction correspondingly).
 * @property adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @property contour If true, contour the results of the 2D density estimation.
 * @property bins Number of levels.
 * @property binWidth Distance between levels.
 */
interface Density2dStatParameters : OptionsCapsule {
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