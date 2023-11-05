/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.SubPlotsAssert.Companion.assertThat
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.themes.flavorDarcula
import org.jetbrains.letsPlot.themes.themeGrey
import org.junit.Test

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
