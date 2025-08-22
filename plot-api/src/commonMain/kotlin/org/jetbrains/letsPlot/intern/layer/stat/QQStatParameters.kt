/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statQQ()][org.jetbrains.letsPlot.stat.statQQ].
 *
 * @property distribution Distribution function to use: "norm", "uniform", "t", "gamma", "exp", "chi2".
 * @property dParams Additional parameters passed on to distribution function.
 */
interface QQStatParameters : OptionsCapsule {
    val distribution: String?
    val dParams: List<Number>?

    override fun seal() = Options.of(
        Stat.QQ.DISTRIBUTION to distribution,
        Stat.QQ.DISTRIBUTION_PARAMETERS to dParams
    )
}