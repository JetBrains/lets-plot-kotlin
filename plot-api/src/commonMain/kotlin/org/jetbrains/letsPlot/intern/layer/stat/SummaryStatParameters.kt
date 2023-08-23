/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

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