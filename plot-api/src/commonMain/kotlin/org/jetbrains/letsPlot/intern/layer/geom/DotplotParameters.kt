/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @property stackDir Which direction to stack the dots.
 *  Values: "up", "down", "center", "centerwhole" (default = "up").
 * @property stackRatio default = 1.0.
 *  How close to stack the dots.
 *  Use smaller values for closer, overlapping dots.
 * @property dotSize default = 1.0.
 *  The diameter of the dots relative to binWidth.
 * @property stackGroups Stack dots across groups when method = "histodot".
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
