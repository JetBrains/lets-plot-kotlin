/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomPolygon
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.toolkit.geotools.toSpatialDataset

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
