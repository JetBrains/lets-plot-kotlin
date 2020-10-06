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

// SpatialDataset is not allowed in 'ggplot(data=..)' or 'lets_plot(data=..)'
//    @Test
//    fun plotDataParam() {
//        val points = listOf(
//            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
//        )
//
//        val dat = SpatialDataset.fromGEOJSON(
//            data = mapOf("cat" to listOf("A")),
//            geometry = points
//        )
//
//        val spec = ggplot(data = dat).toSpec()
//        assertEquals(
//            mapOf(
//                "kind" to "plot",
//                "mapping" to emptyMap<Any, Any>(),
//                "layers" to emptyList<Any>(),
//                "scales" to emptyList<Any>(),
//                "data" to HashMap(dat),
//                "data_meta" to mapOf("geodataframe" to mapOf("geometry" to dat.geometryKey))
//            ),
//            spec
//        )
//    }

    @Test
    fun layerMapParam() {
        // WKT format is not yet supported
//        val points = listOf("POINT (1 2)")
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val dat = SpatialDataset.fromGEOJSON(
            data = emptyMap(),
            geometry = points
        )
        runLayerTest(
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
    fun layerDataParam() {
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val dat = SpatialDataset.fromGEOJSON(
            data = mapOf("cat" to listOf("A")),
            geometry = points
        )

        runLayerTest(
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

    @Test
    fun layerMapAndMapJoinParam() {
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val dat = SpatialDataset.fromGEOJSON(
            data = emptyMap(),
            geometry = points
        )
        runLayerTest(
            ggplot() + geom_point(map = dat, mapJoin = "left" to "right"),
            mapOf(
                "geom" to "point",
                "stat" to "identity",
                "position" to "identity",
                "mapping" to emptyMap<Any, Any>(),
                "map" to dat,
                "map_join" to listOf("left", "right"),
                "map_data_meta" to mapOf("geodataframe" to mapOf("geometry" to dat.geometryKey))
            )
        )
    }

    @Test
    fun layerMapAndMapJoinMultParam() {
        val points = listOf(
            """{"type": "Point", "coordinates": [1.0, 2.0]}"""
        )

        val dat = SpatialDataset.fromGEOJSON(
            data = emptyMap(),
            geometry = points
        )
        runLayerTest(
            ggplot() + geom_point(map = dat, mapJoin = listOf("leftA", "leftB") to listOf("rightA", "rightB")),
            mapOf(
                "geom" to "point",
                "stat" to "identity",
                "position" to "identity",
                "mapping" to emptyMap<Any, Any>(),
                "map" to dat,
                "map_join" to listOf(listOf("leftA", "leftB"), listOf("rightA", "rightB")),
                "map_data_meta" to mapOf("geodataframe" to mapOf("geometry" to dat.geometryKey))
            )
        )
    }


    private fun runLayerTest(
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