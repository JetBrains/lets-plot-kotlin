/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.label

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OtherPlotFeature

/**
 * Add title to the plot.
 *
 * @param title string.
 *     The text for the plot title.
 */
@Suppress("SpellCheckingInspection")
fun ggtitle(title: String): OtherPlotFeature {
    return OtherPlotFeature(
        Option.Plot.TITLE,
        mapOf(
            "text" to title
        )
    )
}
