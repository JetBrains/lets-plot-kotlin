/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.label.ggtitle
import org.junit.Test
import kotlin.test.assertTrue

class PlotTitleTest {

    @Test
    fun `check plot option`() {
        val p = ggplot() + ggtitle("Let's plot!")
        assertThat(p).features().length(1)

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<Any, Any>(),
                "layers" to emptyList<Any>(),
                "scales" to emptyList<Any>(),
                "ggtitle" to mapOf("text" to "Let's plot!")
            ),
            spec
        )
    }

    @Suppress("SpellCheckingInspection")
    @Test
    fun `gggrid title`() {
        val list = listOf(ggplot(), ggplot())
        val grid = gggrid(list) + ggtitle("Let's plot!")

        val spec = grid.toSpec()
        assertTrue(spec.containsKey("ggtitle"))
        assertEquals(
            mapOf("text" to "Let's plot!"),
            spec.getValue("ggtitle")
        )
    }


    @Suppress("SpellCheckingInspection")
    @Test
    fun `ggbunch title`() {
        val list = listOf(ggplot(), ggplot())
        val bunch = ggbunch(list, regions = emptyList()) + ggtitle("Let's plot!")

        val spec = bunch.toSpec()
        assertTrue(spec.containsKey("ggtitle"))
        assertEquals(
            mapOf("text" to "Let's plot!"),
            spec.getValue("ggtitle")
        )
    }
}