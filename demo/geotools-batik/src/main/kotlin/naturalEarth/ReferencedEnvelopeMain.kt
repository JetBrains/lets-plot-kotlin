/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.letsPlot.geom.geomPolygon
import jetbrains.letsPlot.geom.geomRect
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.toolkit.geotools.toSpatialDataset
import org.geotools.geometry.jts.ReferencedEnvelope
import org.geotools.referencing.crs.DefaultGeographicCRS

fun main() {
    // GeoTools
    // World boundaries
    val features = NaturalEarthShp.loadPolygon()

    // South America bounding box
    val envelope = ReferencedEnvelope(
        -85.41094255239946, -30.729993455533034,  // longitudes
        -59.61183, 16.43730316817731,             // latitudes
        DefaultGeographicCRS.WGS84
    )

    val anotherEnvelope = ReferencedEnvelope(envelope)
    anotherEnvelope.translate(60.0, 25.0)

    // Lets-Plot
    val boundaries = features.toSpatialDataset(10)
    val box = envelope.toSpatialDataset()
    val p = letsPlot() +
            geomPolygon(map = boundaries, alpha = 0.2, color = "black") +
            geomRect(map = box, size = 4, color = "orange", alpha = 0) +
            geomRect(map = anotherEnvelope.toSpatialDataset(), size = 4, color = "light-blue", alpha = 0) +
            ggtitle("geom_polygon: 'map'")

    SimpleBatikView.show(
        p.toSpec(),
        DoubleVector(800.0, 500.0)
    )
}
