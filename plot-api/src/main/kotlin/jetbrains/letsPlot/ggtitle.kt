/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OtherPlotFeature

@Suppress("SpellCheckingInspection")
fun ggtitle(title: String): OtherPlotFeature {
    return OtherPlotFeature(
        Option.Plot.TITLE,
        mapOf(
            "text" to title
        )
    )
}
