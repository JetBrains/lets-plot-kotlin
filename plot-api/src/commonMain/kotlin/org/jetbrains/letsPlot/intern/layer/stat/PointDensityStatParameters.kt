/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of the point density stat.
 *
 * @property bw The method (or exact value) of bandwidth. Either a String (choose among "nrd0" and "nrd"),
 *  or a Double array of length 2. Only used when `method = "kde2d"`.
 * @property kernel The kernel used for KDE. One of `"gaussian"`, `"cosine"`, `"optcosine"`, `"rectangular"`/`"uniform"`,
 *  `"triangular"`, `"biweight"`/`"quartic"`, `"epanechikov"`/`"parabolic"`. Only used when `method = "kde2d"`.
 * @property method One of `"auto"`, `"neighbours"`, `"kde2d"` (default = `"auto"`). Method to compute the density estimate:
 *  - `"neighbours"` - estimates density from the number of nearby points.
 *  - `"kde2d"` - estimates density using a smoothed 2D kernel density.
 *  - `"auto"` - automatically selects an estimation method based on data size.
 * @property n The number of sampled points for plotting the function (in x and y direction, respectively).
 *  Only used when `method = "kde2d"`.
 * @property adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 */
interface PointDensityStatParameters : OptionsCapsule {
    val bw: Any?
    val kernel: String?
    val method: String?
    val n: Int?           // ToDo: just Int or a pair of ints?
    val adjust: Number?

    override fun seal() = Options.of(
        "bw" to bw,
        "method" to method,
        "kernel" to kernel,
        "n" to n,
        "adjust" to adjust
    )
}
