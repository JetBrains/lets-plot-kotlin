/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options

/**
 * Properties for parameters of [statYDensity()][org.jetbrains.letsPlot.stat.statYDensity].
 *
 * @property scale How to scale the groups.
 * @property tailsCutoff Extends domain of each violin on `tailsCutoff * bw` if `trim = false`.
 */
interface YDensityStatParameters : DensityStatParameters {
    val scale: String?
    val tailsCutoff: Number?

    override fun seal() = super.seal() + Options.of(
        Stat.YDensity.TAILS_CUTOFF to tailsCutoff,
        Stat.YDensity.SCALE to scale
    )
}