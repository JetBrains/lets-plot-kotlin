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
                """{"type": "Point", "coordinates": [1.0, 1.0]}""",
                """{"type": "Point", "coordinates": [1.0, 2.0]}""",
                """{"type": "Point", "coordinates": [2.0, 1.0]}"""
            )

            val polygons = listOf(
                """{"type": "MultiPolygon", "coordinates": [[[[11.0, 12.0], [13.0, 14.0], [15.0, 13.0], [11.0, 12.0]]]]}"""
            )

            val lines = listOf(
                """{"type": "LineString", "coordinates": [[15.0, 21.0], [29, 14], [33, 19]]}""",
                """{"type": "LineString", "coordinates": [[3.0, 3.0], [7, 7], [10, 10]]}"""
            )

            (lets_plot() +
                    geom_point(
                        map = SpatialDataset.fromGEOJSON(data = emptyMap(), geometry = points),
                        size = 10, color = "magenta"
                    ) +
                    ggtitle("Points")).show()

            (lets_plot() +
                    geom_polygon(
                        map = SpatialDataset.fromGEOJSON(data = emptyMap(), geometry = polygons),
                        fill = "light-gray", color = "black"
                    ) +
                    ggtitle("Polygons")).show()

            (lets_plot() +
                    geom_path(
                        map = SpatialDataset.fromGEOJSON(data = emptyMap(), geometry = lines),
                        color = "light-gray"
                    ) +
                    ggtitle("Lines")).show()
        }
    }
}