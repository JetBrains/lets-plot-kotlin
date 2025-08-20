/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @param stackDir Stacking direction for dots ("up", "down", "center", "centerwhole").
 * @param stackRatio Ratio between dot height and the spacing between dot centers.
 * @param dotSize Relative size of dots compared to bin width.
 * @param stackGroups Whether to stack dots across groups or keep them separate.
 */
interface DotplotParameters : OptionsCapsule {
    val stackDir: String?
    val stackRatio: Number?
    val dotSize: Number?
    val stackGroups: Boolean?

    override fun seal() = Options.of(
        "stackdir" to stackDir,
        "stackratio" to stackRatio,
        "dotsize" to dotSize,
        "stackgroups" to stackGroups,
    )
}
