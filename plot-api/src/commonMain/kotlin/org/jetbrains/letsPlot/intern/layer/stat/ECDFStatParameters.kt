/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [statECDF()][org.jetbrains.letsPlot.stat.statECDF].
 *
 * @property n The number of points to interpolate with.
 * @property pad If geometry is `Geom.step()` and `pad = true`, then the points at the ends:
 *  (Double.NEGATIVE_INFINITY, 0.0) and (Double.POSITIVE_INFINITY, 1.0) are added to the ecdf.
 */
interface ECDFStatParameters : OptionsCapsule {
    val n: Int?
    val pad: Boolean?

    override fun seal() = Options.of(
        Option.Stat.ECDF.N to n,
        Option.Stat.ECDF.PADDED to pad
    )
}