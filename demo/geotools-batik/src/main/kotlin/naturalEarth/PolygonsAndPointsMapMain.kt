/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomPolygon
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset

fun main() {
    // GeoTools
    val polygons = NaturalEarthShp.loadPolygon()
    val cities = NaturalEarthShp.loadCities()

    // Lets-Plot
    val polygonsSD = polygons.toSpatialDataset(10)
    val citiesSD = cities.toSpatialDataset()
    val p = letsPlot() +
            geomPolygon(map = polygonsSD, fill = "white", color = "gray") +
            geomPoint(map = citiesSD, color = "red") +
            ggtitle("geom_polygon, geom_point: 'map'")

    SimpleBatikView.show(
        p.toSpec(),
        DoubleVector(800.0, 500.0)
    )
}
