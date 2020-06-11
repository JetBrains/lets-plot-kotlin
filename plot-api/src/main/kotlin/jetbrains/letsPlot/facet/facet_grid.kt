/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.facet

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OtherPlotFeature
import jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Lay out panels in a grid.
 * @param x string, optional.
 *     The name of a feature, which defines columns of the facet grid to be displayed.
 * @param y string, optional.
 *     The name of a feature, which defines rows of the facet grid to be displayed.
 */
@Suppress("FunctionName")
fun facet_grid(x: String? = null, y: String? = null): OtherPlotFeature {
    return OtherPlotFeature(
        Option.Plot.FACET,
        mapOf(
            "x" to x,
            "y" to y
        ).filterNonNullValues()
    )
}

