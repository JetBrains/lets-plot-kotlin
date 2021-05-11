/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.config.Option.Stat
import jetbrains.letsPlot.intern.Options

interface BoxplotStatParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val varWidth: Boolean?

    @Suppress("SpellCheckingInspection")
    val coef: Number?  // Whisker IQR ratio

    override fun seal() = Options.of(
        Stat.Boxplot.VARWIDTH to varWidth,
        Stat.Boxplot.COEF to coef
    )
}
