/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

/**
 * Params:
 * method - smoothing method: lm, glm, gam, loess, rlm
 * n (80) - number of points to evaluate smoother at
 * se (TRUE ) - display confidence interval around smooth?
 * level (0.95) - level of confidence interval to use
 * deg ( >= 1 ) - degree of polynomial for regression
 * seed  - random seed for LOESS sampling
 * maxn (1000)  - maximum points in DF for LOESS
 */
interface SmoothStatParameters : OptionsCapsule {
    val method: String?
    val n: Int?
    val level: Number?
    val se: Boolean?
    val span: Number?
    val deg: Int?
    val seed: Long?
    val maxn: Int?

    override fun seal() = Options.of(
        "method" to method,
        "n" to n,
        "level" to level,
        "se" to se,
        "span" to span,
        "deg" to deg,
        "seed" to seed,
        "max_n" to maxn
    )
}