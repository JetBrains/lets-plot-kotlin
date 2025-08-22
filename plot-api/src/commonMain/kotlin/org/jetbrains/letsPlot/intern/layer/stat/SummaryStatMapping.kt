/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic mappings supported by [statSummary()][org.jetbrains.letsPlot.stat.statSummary].
 *
 * @param x X-axis coordinates for vertical interval / position of mid-point for horizontal interval.
 * @param y Y-axis coordinates for horizontal interval / position of mid-point for vertical interval.
 */
class SummaryStatMapping(
    val x: Any? = null,
    val y: Any? = null
) : OptionsCapsule {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y
    )
}