/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap

/**
 * Specifies overall size of plot.
 *
 * ## Examples
 *
 * - [quickstart.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quickstart.ipynb)
 *
 * - [geotools_naturalearth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/geotools_naturalearth.ipynb)
 *
 * @param width Width of plot in px.
 * @param height Height of plot in px.
 */
@Suppress("SpellCheckingInspection")
fun ggsize(width: Number, height: Number): OptionsMap {
    return OptionsMap(
        Option.Plot.SIZE,
        mapOf(
            "width" to width,
            "height" to height
        )
    )
}

