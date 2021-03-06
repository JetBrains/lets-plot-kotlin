/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap

/**
 * Specifies overall size of plot.
 * @param width number.
 *     Width of plot in px.
 * @param height number.
 *     Height of plot in px.
 */
@Suppress("SpellCheckingInspection")
fun ggsize(width: Int, height: Int): OptionsMap {
    return OptionsMap(
        Option.Plot.SIZE,
        mapOf(
            "width" to width,
            "height" to height
        )
    )
}

