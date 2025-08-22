/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options

/**
 * Properties for parameters of the default stat of [geomSina()][org.jetbrains.letsPlot.geom.geomSina].
 *
 * @property scale How to scale the groups.
 * @property tailsCutoff Extend domain of each violin on `tailsCutoff * bw` if `trim = false`.
 */
interface SinaStatParameters : DensityStatParameters {
    val scale: String?
    val tailsCutoff: Number?

    override fun seal() = super.seal() + Options.of(
        Stat.Sina.TAILS_CUTOFF to tailsCutoff,
        Stat.YDensity.SCALE to scale // TODO: Replace to Stat.Sina.SCALE
    )
}