/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.SubPlotsAssert.Companion.assertThat
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.themes.flavorDarcula
import org.jetbrains.letsPlot.themes.theme
import org.jetbrains.letsPlot.themes.themeBW
import org.jetbrains.letsPlot.themes.themeGrey
import org.jetbrains.letsPlot.themes.themeLight
import org.junit.Test
import kotlin.test.assertFalse

class ThemePlotGridTest {

    @Test
    fun `grid with theme`() {
        val p = gggrid(
            listOf(
                ggplot()
            )
        ) + themeGrey()

        assertThemeSpec(
            p,
            featureCount = 1,
            expected = mapOf("name" to "grey")
        )
    }

    @Test
    fun `grid with flavor`() {
        val p = gggrid(
            listOf(
                ggplot()
            )
        ) + flavorDarcula()

        assertThemeSpec(
            p,
            featureCount = 1,
            expected = mapOf("flavor" to "darcula")
        )
    }

    @Test
    fun `grid with theme and flavor`() {
        val p = gggrid(
            listOf(
                ggplot()
            )
        ) + themeGrey() + flavorDarcula()

        assertThemeSpec(
            p,
            featureCount = 2,
            expected = mapOf(
                "name" to "grey",
                "flavor" to "darcula"
            )
        )
    }

    @Test
    fun `grid with theme and flavor as array`() {
        val featureArray = flavorDarcula() + themeGrey()
        val p = gggrid(
            listOf(
                ggplot()
            )
        ) + featureArray

        assertThemeSpec(
            p,
            featureCount = 2,
            expected = mapOf(
                "name" to "grey",
                "flavor" to "darcula"
            )
        )
    }

    @Test
    fun `grid global theme applied`() {
        // Set global setting!
        LetsPlot.theme = themeGrey()

        try {
            val p = gggrid(listOf(ggplot()))
            assertThemeSpec(
                p,
                featureCount = 1,
                expected = mapOf(
                    "name" to "grey",
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertFalse(fig.containsKey("theme"))
        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    @Test
    fun `grid global theme override`() {
        // Set global setting!
        LetsPlot.theme = themeGrey()

        try {
            val p = gggrid(listOf(ggplot())) + themeLight() // Override global theme
            assertThemeSpec(
                p,
                featureCount = 2,
                expected = mapOf(
                    "name" to "light",
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertFalse(fig.containsKey("theme"))
        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    @Test
    fun `grid global theme - override named theme but keep global flavor`() {
        // Set global setting!
        LetsPlot.theme = themeGrey() + flavorDarcula()

        try {
            val p = gggrid(listOf(ggplot())) + themeLight() // Override global theme
            assertThemeSpec(
                p,
                featureCount = 3,
                expected = mapOf(
                    "name" to "light",
                    "flavor" to "darcula",
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertFalse(fig.containsKey("theme"))
        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    @Test
    fun `grid global theme override cancelled`() {
        // Set global setting!
        LetsPlot.theme = themeGrey()

        try {
            val element = ggplot() + themeBW()  // this should cancel global theme for this figure
            val p = gggrid(listOf(element)) + themeLight()
            assertThemeSpec(
                p,
                featureCount = 2,
                expected = mapOf(
                    "name" to "light",
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertTrue(fig.containsKey("theme"))

            assertEquals(
                mapOf(
                    "name" to "bw",
                ),
                fig["theme"]
            )

        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    // with theme as list of features

    @Test
    fun `grid global theme as feature list applied`() {
        // Set global settings as combination of theme settings
        LetsPlot.theme = themeGrey() + flavorDarcula() + theme().legendPositionBottom()

        try {
            val p = gggrid(listOf(ggplot()))
            assertThemeSpec(
                p,
                featureCount = 3,
                expected = mapOf(
                    "name" to "grey",
                    "flavor" to "darcula",
                    "legend_position" to "bottom"
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertFalse(fig.containsKey("theme"))
        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    @Test
    fun `grid global theme as feature list override`() {
        // Set global settings as combination os themes
        LetsPlot.theme = themeGrey() + flavorDarcula() // + theme().legendPositionBottom()

        try {
            val p = gggrid(listOf(ggplot())) + themeLight() // Override global named theme
            assertThemeSpec(
                p,
                featureCount = 3,
                expected = mapOf(
                    "name" to "light",
                    "flavor" to "darcula",              // Keep flavor
                    // "legend_position" to "bottom"       // and theme option
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertFalse(fig.containsKey("theme"))
        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    @Test
    fun `grid global theme as feature list override cancelled`() {
        // Set global settings as combination os themes
        LetsPlot.theme = themeGrey() + flavorDarcula() // + theme().legendPositionBottom()

        try {
            val element = ggplot() + themeBW()  // this should cancel global theme for this figure
            val p = gggrid(listOf(element)) + themeLight()
            assertThemeSpec(
                p,
                featureCount = 3,
                expected = mapOf(
                    "name" to "light",
                    "flavor" to "darcula",              // Keep flavor
                )
            )

            // Global theme should be stripped from the figure spec in grid
            val fig = (p.toSpec()["figures"] as List<Any?>)[0] as Map<*, *>
            assertEquals("plot", fig["kind"]) // Make sure it's a plot
            assertTrue(fig.containsKey("theme"))

            assertEquals(
                mapOf(
                    "name" to "bw",
                    "flavor" to "darcula",              // Add flavor
                 // "legend_position" to "bottom"       // and theme option
                ),
                fig["theme"]
            )
        } finally {
            // Clear global setting
            LetsPlot.theme = null
        }
    }

    private companion object {
        fun assertThemeSpec(
            figure: SubPlotsFigure,
            featureCount: Int,
            expected: Map<*, *>
        ) {
            assertThat(figure).features().length(featureCount)
            if (featureCount == 1) {
                val theme = assertThat(figure).features().get(0)
                val options = theme.isOptionsMap()
                    .kind(Option.Plot.THEME)
                    .options
                assertEquals(
                    expected,
                    options
                )
            }

            // test 'toSpec()'
            val spec = figure.toSpec()
            assertEquals(
                expected,
                spec[Option.Plot.THEME]
            )
        }
    }
}
