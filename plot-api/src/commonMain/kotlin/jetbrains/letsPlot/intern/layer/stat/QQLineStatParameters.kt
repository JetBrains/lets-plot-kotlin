/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.config.Option.Stat
import jetbrains.letsPlot.intern.Options

interface QQLineStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val distribution: String?
    val dParams: List<Number>?
    val quantiles: Pair<Number, Number>?

    override fun seal() = Options.of(
        Stat.QQLine.DISTRIBUTION to distribution,
        Stat.QQLine.DISTRIBUTION_PARAMETERS to dParams,
        Stat.QQLine.LINE_QUANTILES to quantiles
    )
}
