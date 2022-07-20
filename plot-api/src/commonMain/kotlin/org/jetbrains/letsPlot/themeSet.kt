/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.builder.defaultTheme.values.ThemeOption
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.OptionsMap


/**
 * Grey background and white gridlines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun themeGrey() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_GREY,
    emptyMap()
)


/**
 * Light grey lines of various widths on white backgrounds.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun themeLight() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_LIGHT,
    emptyMap()
)


/**
 * Black axes and no gridlines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun themeClassic() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_CLASSIC,
    emptyMap()
)


/**
 * A minimalistic theme without axes lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun themeMinimal() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_MINIMAL,
    emptyMap()
)


/**
 * Default theme similar to `themeMinimal()` but with x-axis line and only major grid lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun themeMinimal2() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.LP_MINIMAL,
    emptyMap()
)


/**
 * A completely empty theme.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun themeNone() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.LP_NONE,
    emptyMap()
)

