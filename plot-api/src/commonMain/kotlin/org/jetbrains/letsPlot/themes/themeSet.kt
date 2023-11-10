/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.themes

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap


/**
 * Grey background and white gridlines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeGrey() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_GREY,
    emptyMap()
)


/**
 * Grey lines on white background with dark grey plot border.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeBW() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_BW,
    emptyMap()
)


/**
 * Light grey lines of various widths on white background.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeLight() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_LIGHT,
    emptyMap()
)


/**
 * Dark grey axes and no gridlines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeClassic() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_CLASSIC,
    emptyMap()
)


/**
 * A minimalistic theme without axes lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeMinimal() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.R_MINIMAL,
    emptyMap()
)


/**
 * Default theme similar to `themeMinimal()` but with x-axis line and only major grid lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeMinimal2() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.LP_MINIMAL,
    emptyMap()
)


/**
 * Basic settings are applied.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb)
 */
fun themeNone() = OptionsMap(
    kind = Option.Plot.THEME,
    name = Option.Theme.Name.LP_NONE,
    emptyMap()
)


/**
 * A completely blank (or "void") background theme: no borders, axes, or gridlines.
 *
 * ## Examples
 *
 * - [theme_void.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/theme_void.ipynb)
 */
fun themeVoid() = themeClassic() + theme(
    line = elementBlank(),
    axis = elementBlank()
)
