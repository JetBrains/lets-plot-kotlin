/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.base.stat.BoxplotStat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface BoxplotParameters : OptionsCapsule {
    val varwidth: Any?
    val coef: Any?

    override fun seal() = Options.of(
        BoxplotStat.P_VARWIDTH to varwidth,
        BoxplotStat.P_COEF to coef
    )
}
