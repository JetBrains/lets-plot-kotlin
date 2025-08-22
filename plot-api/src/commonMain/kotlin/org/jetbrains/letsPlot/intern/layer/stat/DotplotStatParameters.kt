/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @property bins Maximum number of bins, used when method is "histodot".
 * @property binWidth When method is "dotdensity", this specifies maximum bin width.
 *  When method is "histodot", this specifies bin width.
 * @property center The center of one of the bins, used when method is "histodot".
 * @property boundary A boundary between two bins, used when method is "histodot".
 * @property method Use "dotdensity" for dot-density binning, or "histodot" for fixed bin widths (like in geomHistogram).
 */
interface DotplotStatParameters : OptionsCapsule {
    val bins: Int?
    val binWidth: Number?
    val center: Number?
    val boundary: Number?
    val method: String?

    override fun seal() = Options.of(
        "bins" to bins,
        "binwidth" to binWidth,
        "center" to center,
        "boundary" to boundary,
        "method" to method,
    )
}
