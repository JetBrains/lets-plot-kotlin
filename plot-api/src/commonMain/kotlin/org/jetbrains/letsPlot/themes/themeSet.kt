/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.themes

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap


/**
 * Set the grey background and white gridlines. The same as `themeGray()`.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeGrey() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_GREY,
    emptyMap()
)


/**
 * Set the gray background and white gridlines. The same as `themeGrey()`.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeGray() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_GRAY,
    emptyMap()
)


/**
 * Set a dark grey plot border and grey gridlines on the white background.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeBW() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_BW,
    emptyMap()
)


/**
 * Set the light grey lines of various widths on the white background.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeLight() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_LIGHT,
    emptyMap()
)


/**
 * Set the dark grey axes and no gridlines on the white background.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeClassic() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_CLASSIC,
    emptyMap()
)


/**
 * Set a minimalistic theme without axes lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeMinimal() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_MINIMAL,
    emptyMap()
)


/**
 * Set the default theme similar to `themeMinimal()` adding an x-axis line and only major gridlines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeMinimal2() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.LP_MINIMAL,
    emptyMap()
)


/**
 * Set a basic blue-accented scheme with the light blue background.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)
 */
fun themeNone() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.LP_NONE,
    emptyMap()
)


/**
 * Set a completely blank (or "void") background theme by removing all non-data elements: no borders, axes, or gridlines.
 *
 * ## Examples
 *
 * - [theme_void.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_void.ipynb)
 */
fun themeVoid() = themeClassic() + theme(
    line = elementBlank(),
    axis = elementBlank()
)
