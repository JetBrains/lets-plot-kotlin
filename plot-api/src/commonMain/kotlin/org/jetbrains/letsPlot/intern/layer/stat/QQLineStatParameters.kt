/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statQQLine()][org.jetbrains.letsPlot.stat.statQQLine].
 *
 * @property distribution Distribution function to use: "norm", "uniform", "t", "gamma", "exp", "chi2".
 * @property dParams Additional parameters passed on to distribution function.
 * @property quantiles Pair of quantiles to use when fitting the Q-Q line.
 */
interface QQLineStatParameters : OptionsCapsule {
    val distribution: String?
    val dParams: List<Number>?
    val quantiles: Pair<Number, Number>?

    override fun seal() = Options.of(
        Stat.QQLine.DISTRIBUTION to distribution,
        Stat.QQLine.DISTRIBUTION_PARAMETERS to dParams,
        Stat.QQLine.LINE_QUANTILES to quantiles
    )
}
