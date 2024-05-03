/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package kotlinIsland

import org.geotools.api.feature.Feature
import org.geotools.api.feature.type.FeatureType
import org.geotools.feature.FeatureCollection
import org.geotools.geojson.feature.FeatureJSON
import java.io.File
import java.nio.file.Paths

/**
 * Loads data and geometries from "Kotlin Island" GeoJSON files.
 */
internal object KotlinIslandGeojson {
    fun loadPlaces(): FeatureCollection<FeatureType, Feature> {
//        return loadResource("docs/examples/data/kotlin_places.geojson")
        return loadResource("docs/examples/data/kotlin_places_no_crs.geojson")
    }

    @Suppress("SameParameterValue")
    private fun loadResource(relativePathname: String): FeatureCollection<FeatureType, Feature> {
        val fileName: String = Paths.get(relativePathname).toAbsolutePath().normalize().toString()

        val geoJSONFile = File(fileName)
        val featureCollection = FeatureJSON().readFeatureCollection(geoJSONFile.reader())
        return featureCollection
    }
}