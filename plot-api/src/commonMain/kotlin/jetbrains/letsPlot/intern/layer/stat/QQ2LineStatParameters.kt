/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options

interface QQ2LineStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val quantiles: Pair<Number, Number>?

    override fun seal() = Options.of(
        "quantiles" to quantiles // ToDo: Use Stat.QQLine.LINE_QUANTILES
    )
}