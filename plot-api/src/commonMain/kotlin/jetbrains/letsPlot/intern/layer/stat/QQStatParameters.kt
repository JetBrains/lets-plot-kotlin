/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.config.Option.Stat
import jetbrains.letsPlot.intern.Options

interface QQStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val distribution: String?
    val dParams: List<Number>?

    override fun seal() = Options.of(
        Stat.QQ.DISTRIBUTION to distribution,
        Stat.QQ.DISTRIBUTION_PARAMETERS to dParams
    )
}