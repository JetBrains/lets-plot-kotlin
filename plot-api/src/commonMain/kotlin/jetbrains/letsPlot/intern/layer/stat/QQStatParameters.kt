/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options

interface QQStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val distribution: String?
    val dParams: List<Number>?

    override fun seal() = Options.of(
        // ToDo Use from jetbrains.datalore.plot.config.Option.Stat:
        //  Stat.QQ.DISTRIBUTION, Stat.QQ.DISTRIBUTION_PARAMETERS
        "distribution" to distribution,
        "dparams" to dParams
    )
}