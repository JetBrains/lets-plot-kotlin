/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option
import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.themes.*
import org.junit.Test
import java.awt.Color
import kotlin.test.assertNull

class ThemeTest {

    @Test
    fun `plot with simple theme`() {
        val p = ggplot() +
                theme(axisTitleY = elementBlank())
                    .legendPositionNone()

        assertThat(p).features().length(1)
        val theme = assertThat(p).features().get(0)
        val options = theme.isOptionsMap()
            .kind(Option.Plot.THEME)
            .options

        assertEquals(
            mapOf(
                "axis_title_y" to mapOf("blank" to true),
                "legend_position" to "none"
            ),
            options
        )

        val spec = p.toSpec()
        assertEquals(
            options,
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `plot with additional theme`() {
        val p0 = ggplot() + theme(axisTitleY = "blank")
        val p = p0 + theme().legendPositionNone()

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "axis_title_y" to "blank",
                "legend_position" to "none"
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `plot with sum of themes`() {
        val themes = theme(axisTitleY = "blank") + theme()
            .legendPositionNone()
        val p = ggplot() + themes

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "axis_title_y" to "blank",
                "legend_position" to "none"
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `theme elements merged`() {
        val themes = theme(axisLine = elementLine(color = "#0000FF")) +
                theme(axisLine = elementLine(size = 10))
        val p = ggplot() + themes

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "axis_line" to mapOf(
                    "color" to "#0000FF",
                    "size" to 10.0,
                    "blank" to false
                ),
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `named theme overrides other theme`() {
        val themes =
            theme(line = elementLine(color = "#000000")) + themeNone()
        val p = ggplot() + themes

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "name" to "none",
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `element color standardized`() {
        val themes = theme(
            axisLineX = elementLine(color = Color.BLACK),
            axisLineY = elementLine(color = org.jetbrains.letsPlot.commons.values.Color.BLUE)
        )
        val p = ggplot() + themes

        assertThat(p).features().length(1)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "axis_line_x" to mapOf("color" to "#000000", "blank" to false),
                "axis_line_y" to mapOf("color" to "#0000ff", "blank" to false),
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `global theme`() {
        LetsPlot.theme = themeGrey()
        assertEquals(
            mapOf("name" to "grey"),
            ggplot().toSpec()[Option.Plot.THEME]
        )

        LetsPlot.theme = null
        assertNull(
            ggplot().toSpec()[Option.Plot.THEME]
        )
    }

    @Test
    fun `named theme should not override the flavor`() {
        val p = ggplot() + flavorDarcula() + themeNone()

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "name" to "none",
                "flavor" to "darcula",
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `add the flavor to the named theme`() {
        val p = ggplot() + themeNone() + flavorDarcula()

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "name" to "none",
                "flavor" to "darcula",
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `the last specified flavor should be used`() {
        val p = ggplot() + flavorSolarizedDark() + flavorDarcula()

        assertThat(p).features().length(2)
        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "flavor" to "darcula",
            ),
            spec[Option.Plot.THEME]
        )
    }

    @Test
    fun `global named theme or flavor`() {
        LetsPlot.theme = themeNone()
        val p1 = ggplot() + flavorDarcula()
        assertEquals(
            mapOf(
                "name" to "none",
                "flavor" to "darcula",
            ),
            p1.toSpec()[Option.Plot.THEME]
        )

        LetsPlot.theme = flavorDarcula()
        val p2 = ggplot() + themeNone()
        assertEquals(
            mapOf(
                "name" to "none",
                "flavor" to "darcula",
            ),
            p2.toSpec()[Option.Plot.THEME]
        )

        LetsPlot.theme = null
    }

    @Test
    fun `global theme as features list`() {
        LetsPlot.setTheme(themeNone() + flavorDarcula() + theme().legendPositionBottom())

        val p = ggplot()
        assertEquals(
            mapOf(
                "name" to "none",
                "flavor" to "darcula",
                "legend_position" to "bottom"
            ),
            p.toSpec()[Option.Plot.THEME]
        )
        // Clear global setting
        LetsPlot.theme = null
    }

    @Test
    fun `global theme as features list for gggrid`() {
        LetsPlot.setTheme(themeNone() + flavorDarcula() + theme().legendPositionBottom())

        val p = gggrid(listOf(ggplot(), ggplot()))
        assertEquals(
            mapOf(
                "name" to "none",
                "flavor" to "darcula",
                "legend_position" to "bottom"
            ),
            p.toSpec()[Option.Plot.THEME]
        )
        // Clear global setting
        LetsPlot.theme = null
    }
}
