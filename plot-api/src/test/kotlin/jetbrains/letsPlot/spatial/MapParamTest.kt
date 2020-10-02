/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.spatial

import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
import jetbrains.letsPlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapParamTest {

    @Test
    fun `only map`() {
        // WKT format is not yet supported
//        val points = listOf("POINT (1 2)")
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val sds = SpatialDataset.fromGEOJSON(
            data = emptyMap(),
            geometry = points
        )
        val p = ggplot() + geom_point(map = sds)
        assertThat(p).features().length(1)

        val spec = p.toSpec()

        val expectedMap = sds
        val expectedGeometryKey = sds.geometryKey
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<Any, Any>(),
                "layers" to listOf(
                    mapOf(
                        "geom" to "point",
                        "stat" to "identity",
                        "position" to "identity",
                        "mapping" to emptyMap<Any, Any>(),
                        "map" to expectedMap,
                        "map_data_meta" to mapOf("geodataframe" to mapOf("geometry" to expectedGeometryKey))
                    )
                ),
                "scales" to emptyList<Any>()
            ),
            spec
        )
    }
}