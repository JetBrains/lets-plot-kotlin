/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.letsPlot.geom.geom_polygon
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.toolkit.geotools.toSpatialDatasetGEOJSON
import org.geotools.data.shapefile.ShapefileDataStoreFactory
import java.nio.file.Paths

fun main() {
    // GeoTools
    val factory = ShapefileDataStoreFactory()
    val shapefileUrl =
        Paths.get("demo/geotools-batik/src/main/resources/naturalearth_lowres/naturalearth_lowres.shp")
            .toAbsolutePath().normalize().toUri().toURL()
    val dataStore = factory.createDataStore(shapefileUrl)
    val features = dataStore.featureSource.features

    // Plot
    val spatialDataset = features.toSpatialDatasetGEOJSON(10)
    val p = lets_plot() + geom_polygon(map = spatialDataset, alpha = 0.2, color = "black")

    SimpleBatikDemo.show(
        p.toSpec(),
        DoubleVector(800.0, 500.0)
    )
}
