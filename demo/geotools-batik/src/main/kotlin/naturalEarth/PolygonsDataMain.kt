/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import org.jetbrains.letsPlot.geom.geomPolygon
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset

fun main() {
    // GeoTools
    val features = NaturalEarthShp.loadPolygon()

    // Lets-Plot
    val spatialDataset = features.toSpatialDataset(10)
    val p = letsPlot() + geomPolygon(data = spatialDataset, alpha = 0.2, color = "black") {
        fill = "continent"
    } + ggtitle("geom_polygon: 'data'")

    SimpleBatikView.show(
        p.toSpec(),
        DoubleVector(800.0, 500.0)
    )
}
