/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.label

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Adds title to the plot.
 *
 * ## Examples
 *
 * - [title_subtitle_caption.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/title_subtitle_caption.ipynb)
 *
 * @param title The text for the plot title.
 * @param subtitle The text for the plot subtitle.
 */
@Suppress("SpellCheckingInspection")
fun ggtitle(title: String, subtitle: String? = null): OptionsMap {
    return OptionsMap(
        Option.Plot.TITLE,
        mapOf(
            Option.Plot.TITLE_TEXT to title,
            Option.Plot.SUBTITLE_TEXT to subtitle
        ).filterNonNullValues()
    )
}
