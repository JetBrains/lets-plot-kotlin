/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.spatial

import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
import jetbrains.letsPlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SpatialParamTest {

    @Test
    fun mapParam() {
        // WKT format is not yet supported
//        val points = listOf("POINT (1 2)")
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val dat = SpatialDataset.fromGEOJSON(
            data = emptyMap(),
            geometry = points
        )
        runTest(
            ggplot() + geom_point(map = dat),
            mapOf(
                "geom" to "point",
                "stat" to "identity",
                "position" to "identity",
                "mapping" to emptyMap<Any, Any>(),
                "map" to dat,
                "map_data_meta" to mapOf("geodataframe" to mapOf("geometry" to dat.geometryKey))
            )
        )
    }

    @Test
    fun dataParam() {
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val dat = SpatialDataset.fromGEOJSON(
            data = mapOf("cat" to listOf("A")),
            geometry = points
        )

        runTest(
            ggplot() + geom_point(data = dat),
            mapOf(
                "geom" to "point",
                "stat" to "identity",
                "position" to "identity",
                "mapping" to emptyMap<Any, Any>(),
                "data" to HashMap(dat),
                "data_meta" to mapOf("geodataframe" to mapOf("geometry" to dat.geometryKey))
            )
        )
    }

    private fun runTest(
        p: Plot,
        expectedLayerSpec: Map<String, Any>
    ) {
        assertThat(p).features().length(1)

        val spec = p.toSpec()

        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<Any, Any>(),
                "layers" to listOf(expectedLayerSpec),
                "scales" to emptyList<Any>()
            ),
            spec
        )
    }
}