/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.themes

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap


/**
 * Darcula color scheme.
 *
 * ## Examples
 *
 * - [theme_flavors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_flavors.ipynb)
 */
fun flavorDarcula() = OptionsMap(
    kind = Option.Plot.THEME,
    options = mapOf(Option.Theme.FLAVOR to Option.Theme.Flavor.DARCULA)
)

/**
 * Solarized light color scheme.
 *
 * ## Examples
 *
 * - [theme_flavors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_flavors.ipynb)
 */
fun flavorSolarizedLight() = OptionsMap(
    kind = Option.Plot.THEME,
    options = mapOf(Option.Theme.FLAVOR to Option.Theme.Flavor.SOLARIZED_LIGHT)
)

/**
 * Solarized dark color scheme.
 *
 * ## Examples
 *
 * - [theme_flavors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_flavors.ipynb)
 */
fun flavorSolarizedDark() = OptionsMap(
    kind = Option.Plot.THEME,
    options = mapOf(Option.Theme.FLAVOR to Option.Theme.Flavor.SOLARIZED_DARK)
)

/**
 * High contrast light color scheme.
 *
 * ## Examples
 *
 * - [theme_flavors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_flavors.ipynb)
 */
fun flavorHighContrastLight() = OptionsMap(
    kind = Option.Plot.THEME,
    options = mapOf(Option.Theme.FLAVOR to Option.Theme.Flavor.HIGH_CONTRAST_LIGHT)
)

/**
 * High contrast dark color scheme.
 *
 * ## Examples
 *
 * - [theme_flavors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_flavors.ipynb)
 */
fun flavorHighContrastDark() = OptionsMap(
    kind = Option.Plot.THEME,
    options = mapOf(Option.Theme.FLAVOR to Option.Theme.Flavor.HIGH_CONTRAST_DARK)
)
