/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.builder.defaultTheme.values.ThemeOption
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap


/**
 * Grey background and white gridlines.
 */
fun themeGrey() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_GREY,
    emptyMap()
)


/**
 * Light grey lines of various widths on white backgrounds.
 */
fun themeLight() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_LIGHT,
    emptyMap()
)


/**
 * Black axes and no gridlines.
 */
fun themeClassic() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_CLASSIC,
    emptyMap()
)


/**
 * A minimalistic theme without axes lines.
 */
fun themeMinimal() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.R_MINIMAL,
    emptyMap()
)


/**
 * Default theme similar to `themeMinimal()` but with x-axis line and only major grid lines.
 */
fun themeMinimal2() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.LP_MINIMAL,
    emptyMap()
)


/**
 * A completely empty theme.
 */
fun themeNone() = OptionsMap(
    kind = Option.Plot.THEME,
    name = ThemeOption.Name.LP_NONE,
    emptyMap()
)

