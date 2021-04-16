/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
import jetbrains.letsPlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ThemeTest {

    @Test
    fun `plot with simple theme`() {
        val p = ggplot() +
                theme()
                    .axisTitleYBlank()
                    .legendPositionNone()

        assertThat(p).features().length(1)
        val theme = assertThat(p).features().get(0)
        val options = theme.isOptionsMap()
            .kind(Option.Plot.THEME)
            .options

        assertEquals(
            mapOf(
                "axis_title_y" to "blank",
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
        val p0 = ggplot() + theme().axisTitleYBlank()
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
        val themes = theme().axisTitleYBlank() + theme().legendPositionNone()
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
}