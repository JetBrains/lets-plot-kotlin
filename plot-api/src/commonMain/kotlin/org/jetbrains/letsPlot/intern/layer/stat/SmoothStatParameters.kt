/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statSmooth()][org.jetbrains.letsPlot.stat.statSmooth].
 *
 * @property method Smoothing method: lm (Linear Model) or loess (Locally Estimated Scatterplot Smoothing).
 * @property n Number of points to evaluate smoother at.
 * @property level Level of confidence interval to use.
 * @property se To display confidence interval around smooth.
 * @property span The fraction of source points closest to the current point is taken into account for computing a least-squares regression. A sensible value is usually 0.25 to 0.5.
 * @property deg Degree of polynomial for linear regression model.
 * @property seed Random seed for LOESS sampling.
 * @property maxN Maximum number of data-points for LOESS method. If this quantity exceeded random sampling is applied to data.
 */
interface SmoothStatParameters : OptionsCapsule {
    val method: String?
    val n: Int?
    val level: Number?
    val se: Boolean?
    val span: Number?
    val deg: Int?
    val seed: Long?
    val maxN: Int?

    override fun seal() = Options.of(
        "method" to method,
        "n" to n,
        "level" to level,
        "se" to se,
        "span" to span,
        "deg" to deg,
        "seed" to seed,
        "max_n" to maxN
    )
}