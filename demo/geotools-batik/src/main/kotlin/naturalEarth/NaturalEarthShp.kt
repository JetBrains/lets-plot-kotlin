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
    fun loadPolygon(): SimpleFeatureCollection {
        return loadResource("docs/examples/shp/naturalearth_lowres/naturalearth_lowres.shp")
    }

    fun loadCities(): SimpleFeatureCollection {
        return loadResource("docs/examples/shp/naturalearth_cities/naturalearth_cities.shp")
    }

    private fun loadResource(relativePathname: String): SimpleFeatureCollection {
        // GeoTools
        val factory = ShapefileDataStoreFactory()
        val shapefileUrl = Paths.get(relativePathname).toAbsolutePath().normalize().toUri().toURL()
        val dataStore = factory.createDataStore(shapefileUrl)
        return dataStore.featureSource.features
    }
}