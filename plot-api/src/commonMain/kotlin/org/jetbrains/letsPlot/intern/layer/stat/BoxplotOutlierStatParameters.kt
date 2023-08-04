/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BoxplotOutlierStatParameters : OptionsCapsule {
    @Suppress("SpellCheckingInspection")
    val coef: Number?  // Whisker IQR ratio

    override fun seal() = Options.of(
        Option.Stat.Boxplot.COEF to coef
    )
}