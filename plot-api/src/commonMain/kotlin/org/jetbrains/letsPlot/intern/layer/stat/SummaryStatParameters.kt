/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statSummary()][org.jetbrains.letsPlot.stat.statSummary].
 *
 * @property fn Name of function computing stat variable `..y..`.
 * @property fnMin Name of function computing stat variable `..ymin..`.
 * @property fnMax Name of function computing stat variable `..ymax..`.
 * @property quantiles A list of probabilities defining the quantile functions "lq", "mq" and "uq". Must contain exactly 3 values between 0 and 1.
 */
interface SummaryStatParameters : OptionsCapsule {
    val fn: String?
    val fnMin: String?
    val fnMax: String?
    val quantiles: List<Number>?

    override fun seal() = Options.of(
        Stat.Summary.FUN to fn,
        Stat.Summary.FUN_MIN to fnMin,
        Stat.Summary.FUN_MAX to fnMax,
        Stat.Summary.QUANTILES to quantiles
    )
}