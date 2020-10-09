/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_path
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_polygon
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.spatial.SpatialDataset

object GeoSpatial {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Geo-spatial: parameters 'map' and 'mapJoin'") {
            val points = listOf(
                """{"type": "Point", "coordinates": [1, 1]}""",
                """{"type": "Point", "coordinates": [2, 1]}"""
            )

            val polygons = listOf(
                """{"type": "MultiPolygon", "coordinates": [[[[11, 12], [13, 14], [15, 13], [11, 12]]]]}""",
                """{"type": "MultiPolygon", "coordinates": [[[[11, 10], [12, 6], [15, 4], [11, 10]]]]}"""
            )

            val lines = listOf(
                """{"type": "LineString", "coordinates": [[15, 21], [29, 14], [33, 19]]}""",
                """{"type": "LineString", "coordinates": [[3, 3], [7, 7], [10, 10]]}"""
            )

            (lets_plot() +
                    geom_point(
                        map = SpatialDataset.withGEOJSON(data = emptyMap(), geometry = points),
                        size = 10, color = "magenta"
                    ) +
                    ggtitle("map = Points")).show()

            (lets_plot() +
                    geom_polygon(
                        map = SpatialDataset.withGEOJSON(data = emptyMap(), geometry = polygons),
                        fill = "light-gray", color = "black"
                    ) +
                    ggtitle("map = Polygons")).show()

            (lets_plot() +
                    geom_path(
                        map = SpatialDataset.withGEOJSON(data = emptyMap(), geometry = lines),
                        color = "light-gray"
                    ) +
                    ggtitle("map = Lines")).show()

            //
            // SpatialDataset in the 'data' parameter
            //
            val data = mapOf("cat" to listOf("A", "B"))

            (lets_plot() +
                    geom_point(
                        data = SpatialDataset.withGEOJSON(data = data, geometry = points),
                        size = 10
                    ) { color = "cat" } +
                    ggtitle("data = Points")).show()

            (lets_plot(data = SpatialDataset.withGEOJSON(data = data, geometry = points)) +
                    geom_point(
                        size = 10
                    ) { color = "cat" } +
                    ggtitle("lets_plot(data = Points) : x,y - not working")).show()

            (lets_plot() +
                    geom_polygon(
                        data = SpatialDataset.withGEOJSON(data = data, geometry = polygons),
                        color = "black"
                    ) { fill = "cat" } +
                    ggtitle("data = Polygons")).show()

            (lets_plot() +
                    geom_path(
                        data = SpatialDataset.withGEOJSON(data = data, geometry = lines)
                    ) { color = "cat" } +
                    ggtitle("data = Lines")).show()
        }
    }
}