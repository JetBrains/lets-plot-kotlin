/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.spatial

import org.jetbrains.letsPlot.geom.geomMap
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class SpatialCRSCodeTest {

    @Test
    fun WGS84() {
        val dat = SpatialDataset.withGEOJSON(
            data = emptyMap(),
            geometry = polygons,
            crs = WGS84
        )
        val p = ggplot() + geomMap(map = dat)
        assertTrue(p.toSpec().isNotEmpty())
    }

    @Test
    fun `Use geometry of non WGS84 CRS`() {
        val dat = SpatialDataset.withGEOJSON(
            data = emptyMap(),
            geometry = polygons,
            crs = NAD83
        )
        assertFailsWith(IllegalArgumentException::class) {
            (ggplot() + geomMap(map = dat)).toSpec()
        }.let { exception ->
            assertEquals(
                "Geometry must use WGS84 coordinate reference system but was: NAD83.",
                exception.message
            )
        }
    }

    @Test
    fun `Use keyword 'provided' to keep the original CRS`() {
        val dat = SpatialDataset.withGEOJSON(
            data = emptyMap(),
            geometry = polygons,
            crs = NAD83
        )
        val p = ggplot() + geomMap(map = dat, useCRS = "provided")
        assertTrue(p.toSpec().isNotEmpty())
    }

    companion object {
        private const val WGS84 = "WGS84"
        private const val NAD83 = "NAD83"

        private val polygons = listOf(
            """{"type": "MultiPolygon", "coordinates": [[[[1, 1], [1, 2], [2, 2], [2, 1]]]]}"""
        )
    }
}