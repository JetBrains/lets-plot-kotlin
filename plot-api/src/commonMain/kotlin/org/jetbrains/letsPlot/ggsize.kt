/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.OptionsMap

/**
 * Specifies overall size of plot.
 *
 * ## Examples
 *
 * - [quickstart.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb)
 *
 * - [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)
 *
 * @param width Number, width of plot in px.
 * @param height Number, height of plot in px.
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

