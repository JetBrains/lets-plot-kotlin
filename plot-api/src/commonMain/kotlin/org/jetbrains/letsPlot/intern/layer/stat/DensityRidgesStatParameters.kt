/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options

/**
 * Properties for parameters of [statDensityRidges()][org.jetbrains.letsPlot.stat.statDensityRidges].
 *
 * @property tailsCutoff Extends domain of each ridge on `tailsCutoff * bw` if `trim = false`.
 *  `tailsCutoff = null` (default) extends domain to maximum (domain overall ridges).
 */
interface DensityRidgesStatParameters: DensityStatParameters {
    val tailsCutoff: Number?

    override fun seal() = super.seal() + Options.of(
        Stat.DensityRidges.TAILS_CUTOFF to tailsCutoff
    )
}