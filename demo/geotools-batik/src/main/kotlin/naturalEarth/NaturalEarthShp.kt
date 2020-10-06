/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import org.geotools.data.shapefile.ShapefileDataStoreFactory
import org.geotools.data.simple.SimpleFeatureCollection
import java.nio.file.Paths

/**
 * Loads the world boundaries from naturalearth_lowres.shp
 */
object NaturalEarthShp {
    fun load(): SimpleFeatureCollection {
        // GeoTools
        val factory = ShapefileDataStoreFactory()
        val shapefileUrl =
            Paths.get("demo/geotools-batik/src/main/resources/naturalearth_lowres/naturalearth_lowres.shp")
                .toAbsolutePath().normalize().toUri().toURL()
        val dataStore = factory.createDataStore(shapefileUrl)
        return dataStore.featureSource.features
    }
}