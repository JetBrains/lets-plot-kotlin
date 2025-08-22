/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statBoxplot()][org.jetbrains.letsPlot.stat.statBoxplot].
 *
 * @property varWidth If false make a standard box plot.
 *  If true, boxes are drawn with widths proportional to the square-roots of the number of
 *  observations in the groups.
 * @property coef Length of the whiskers as multiple of IQR.
 */
interface BoxplotStatParameters : OptionsCapsule {
    val varWidth: Boolean?

    @Suppress("SpellCheckingInspection")
    val coef: Number?  // Whisker IQR ratio

    override fun seal() = Options.of(
        Stat.Boxplot.VARWIDTH to varWidth,
        Stat.Boxplot.COEF to coef
    )
}
