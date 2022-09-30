/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.font

import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.letsPlot
import org.junit.Test
import kotlin.test.assertEquals

class FontFeaturesTest {

    @Test
    fun `default width factor`() {
        val p = letsPlot() +
                fontMetricsAdjustment(2.0) +
                fontMetricsAdjustment(3.0)

        @Suppress("UNCHECKED_CAST")
        val specList = p.toSpec()["metainfo_list"] as List<Map<String, Any>>
        assertEquals(2, specList.size)
        val names = specList.map { it["name"] as String }
        val values = specList.map { (it["width_correction"] as Number).toDouble() }
        assertEquals(
            listOf("font_metrics_adjustment", "font_metrics_adjustment"),
            names
        )
        assertEquals(
            listOf(2.0, 3.0),
            values
        )
    }

    @Test
    fun `family info`() {
        val p = letsPlot() +
                fontFamilyInfo(family = FAMILY, 2.0, mono = false) +
                fontFamilyInfo(family = FAMILY_MONO, 3.0, mono = true)

        @Suppress("UNCHECKED_CAST")
        val specList = p.toSpec()["metainfo_list"] as List<Map<String, Any>>
        assertEquals(2, specList.size)
        val names = specList.map { it["name"] as String }
        val values = specList.map { (it["width_correction"] as Number).toDouble() }
        val monoFlags = specList.map { it["monospaced"] as Boolean }
        assertEquals(
            listOf("font_family_info", "font_family_info"),
            names
        )
        assertEquals(
            listOf(2.0, 3.0),
            values
        )
        assertEquals(
            listOf(false, true),
            monoFlags
        )
    }

    @Test
    fun `no parameters family info`() {
        val p = letsPlot() +
                fontFamilyInfo(family = FAMILY)

        @Suppress("UNCHECKED_CAST")
        val specList = p.toSpec()["metainfo_list"] as List<Map<String, Any>>
        assertEquals(1, specList.size)
        val names = specList.map { it["name"] as String }
        val hasCorrectionValue = specList.map { it.containsKey("width_correction") }
        val hasMonoFlags = specList.map { it.containsKey("monospaced") }
        assertEquals(
            listOf("font_family_info"),
            names
        )
        assertEquals(
            listOf(false),
            hasCorrectionValue
        )
        assertEquals(
            listOf(false),
            hasMonoFlags
        )
    }


    private companion object {
        const val FAMILY = "Tested font family"
        const val FAMILY_MONO = "Courier" // Pre-registered
    }

}