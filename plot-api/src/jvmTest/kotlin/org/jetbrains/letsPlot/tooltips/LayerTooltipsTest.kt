/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.tooltips

import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.core.spec.Option
import org.junit.Test

class LayerTooltipsTest {

    // Helper to get options as a map (for non-"none" cases).
    @Suppress("UNCHECKED_CAST")
    private val layerTooltips.asMap get() = options as Map<String, Any>

    // ---- constructors -------------------------------------------------------

    @Test
    fun `empty constructor produces empty options`() {
        assertEquals(emptyMap<String, Any>(), layerTooltips().asMap)
    }

    @Test
    fun `vararg variables`() {
        assertEquals(
            mapOf(Option.LinesSpec.VARIABLES to listOf("x", "y", "z")),
            layerTooltips("x", "y", "z").asMap
        )
    }

    @Test
    fun `single vararg variable`() {
        assertEquals(
            mapOf(Option.LinesSpec.VARIABLES to listOf("x")),
            layerTooltips("x").asMap
        )
    }

    @Test
    fun `list constructor matches vararg`() {
        assertEquals(
            layerTooltips("x", "y", "z").asMap,
            layerTooltips(listOf("x", "y", "z")).asMap
        )
    }

    @Test
    fun `empty list constructor produces empty options`() {
        assertEquals(emptyMap<String, Any>(), layerTooltips(emptyList()).asMap)
    }

    // ---- none ---------------------------------------------------------------

    @Test
    fun `tooltipsNone produces none string`() {
        assertEquals(Option.Layer.NONE, tooltipsNone.options)
    }

    // ---- format -------------------------------------------------------------

    @Test
    fun `format adds entry to formats list`() {
        val options = layerTooltips().format("@x", ".2f").asMap
        assertEquals(
            listOf(mapOf(Option.LinesSpec.Format.FIELD to "@x", Option.LinesSpec.Format.FORMAT to ".2f")),
            options[Option.LinesSpec.FORMATS]
        )
    }

    @Test
    fun `multiple format calls accumulate`() {
        val formats = layerTooltips()
            .format("@x", ".2f")
            .format("@y", ".3f")
            .asMap[Option.LinesSpec.FORMATS] as List<*>
        assertEquals(2, formats.size)
    }

    // ---- line ---------------------------------------------------------------

    @Test
    fun `line adds template to lines list`() {
        assertEquals(
            mapOf(Option.LinesSpec.LINES to listOf("x = @x")),
            layerTooltips().line("x = @x").asMap
        )
    }

    @Test
    fun `multiple line calls accumulate in order`() {
        val lines = layerTooltips()
            .line("x = @x")
            .line("y = @y")
            .asMap[Option.LinesSpec.LINES] as List<*>
        assertEquals(listOf("x = @x", "y = @y"), lines)
    }

    // ---- title --------------------------------------------------------------

    @Test
    fun `title sets tooltip title`() {
        assertEquals(
            mapOf(Option.LinesSpec.TITLE to "My Title"),
            layerTooltips().title("My Title").asMap
        )
    }

    // ---- anchor -------------------------------------------------------------

    @Test
    fun `anchor sets tooltip anchor`() {
        assertEquals(
            mapOf(Option.Layer.TOOLTIP_ANCHOR to "top_left"),
            layerTooltips().anchor("top_left").asMap
        )
    }

    // ---- minWidth -----------------------------------------------------------

    @Test
    fun `minWidth sets minimum tooltip width`() {
        assertEquals(
            mapOf(Option.Layer.TOOLTIP_MIN_WIDTH to 200),
            layerTooltips().minWidth(200).asMap
        )
    }

    // ---- disableSplitting ---------------------------------------------------

    @Test
    fun `disableSplitting sets flag to true`() {
        assertEquals(
            mapOf(Option.Layer.DISABLE_SPLITTING to true),
            layerTooltips().disableSplitting().asMap
        )
    }

    // ---- immutability -------------------------------------------------------

    @Test
    fun `methods do not mutate the original instance`() {
        val original = layerTooltips("x")
        original.line("template")
        original.title("Title")
        original.format("@x", ".2f")
        assertEquals(
            mapOf(Option.LinesSpec.VARIABLES to listOf("x")),
            original.asMap
        )
    }

    // ---- chaining -----------------------------------------------------------

    @Test
    fun `chaining multiple methods`() {
        val options = layerTooltips("x", "y")
            .title("Title")
            .line("x = @x")
            .format("@x", ".2f")
            .anchor("top_right")
            .asMap

        assertEquals(listOf("x", "y"), options[Option.LinesSpec.VARIABLES])
        assertEquals("Title", options[Option.LinesSpec.TITLE])
        assertEquals("top_right", options[Option.Layer.TOOLTIP_ANCHOR])
        assertEquals(listOf("x = @x"), options[Option.LinesSpec.LINES])
    }
}
