/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [geomHistogram()][org.jetbrains.letsPlot.geom.geomHistogram].
 *
 * @property breaks Exact positions of bin boundaries.
 */
interface HistogramParameters : OptionsCapsule {
    val breaks: List<Number>?

    override fun seal() = Options.of(
        Option.Geom.Histogram.BREAKS to breaks
    )
}
