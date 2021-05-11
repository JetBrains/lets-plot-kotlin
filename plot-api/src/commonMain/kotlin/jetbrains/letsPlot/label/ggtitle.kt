/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.label

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap

/**
 * Add title to the plot.
 *
 * @param title string.
 *     The text for the plot title.
 */
@Suppress("SpellCheckingInspection")
fun ggtitle(title: String): OptionsMap {
    return OptionsMap(
        Option.Plot.TITLE,
        mapOf(
            "text" to title
        )
    )
}
