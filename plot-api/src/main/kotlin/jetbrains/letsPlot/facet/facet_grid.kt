/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.facet

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OtherPlotFeature
import jetbrains.letsPlot.intern.filterNonNullValues

@Suppress("SpellCheckingInspection")
fun facet_grid(x: String? = null, y: String? = null): OtherPlotFeature {
    return OtherPlotFeature(
        Option.Plot.FACET,
        mapOf(
            "x" to x,
            "y" to y
        ).filterNonNullValues()
    )
}

