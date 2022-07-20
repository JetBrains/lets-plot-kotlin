/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomPath
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomPolygon
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.spatial.SpatialDataset

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

            (letsPlot() +
                    geomPoint(
                        map = SpatialDataset.withGEOJSON(data = emptyMap(), geometry = points),
                        size = 10, color = "magenta"
                    ) +
                    ggtitle("map = Points")).show()

            (letsPlot() +
                    geomPolygon(
                        map = SpatialDataset.withGEOJSON(data = emptyMap(), geometry = polygons),
                        fill = "light-gray", color = "black"
                    ) +
                    ggtitle("map = Polygons")).show()

            (letsPlot() +
                    geomPath(
                        map = SpatialDataset.withGEOJSON(data = emptyMap(), geometry = lines),
                        color = "light-gray"
                    ) +
                    ggtitle("map = Lines")).show()

            //
            // SpatialDataset in the 'data' parameter
            //
            val data = mapOf("cat" to listOf("A", "B"))

            (letsPlot() +
                    geomPoint(
                        data = SpatialDataset.withGEOJSON(data = data, geometry = points),
                        size = 10
                    ) { color = "cat" } +
                    ggtitle("data = Points")).show()

            (letsPlot(data = SpatialDataset.withGEOJSON(data = data, geometry = points)) +
                    geomPoint(
                        size = 10
                    ) { color = "cat" } +
                    ggtitle("lets_plot(data = Points) : x,y - not working")).show()

            (letsPlot() +
                    geomPolygon(
                        data = SpatialDataset.withGEOJSON(data = data, geometry = polygons),
                        color = "black"
                    ) { fill = "cat" } +
                    ggtitle("data = Polygons")).show()

            (letsPlot() +
                    geomPath(
                        data = SpatialDataset.withGEOJSON(data = data, geometry = lines)
                    ) { color = "cat" } +
                    ggtitle("data = Lines")).show()
        }
    }
}