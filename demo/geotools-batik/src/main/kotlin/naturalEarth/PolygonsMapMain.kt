/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.letsPlot.geom.geomPolygon
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.toolkit.geotools.toSpatialDataset

fun main() {
    // GeoTools
    val features = NaturalEarthShp.loadPolygon()

    // Lets-Plot
    val spatialDataset = features.toSpatialDataset(10)
    val p = letsPlot() + geomPolygon(map = spatialDataset, alpha = 0.2, color = "black") +
            ggtitle("geom_polygon: 'map'")

    SimpleBatikView.show(
        p.toSpec(),
        DoubleVector(800.0, 500.0)
    )
}
