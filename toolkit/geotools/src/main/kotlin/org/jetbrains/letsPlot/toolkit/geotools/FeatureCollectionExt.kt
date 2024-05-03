/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.geotools

import org.geotools.api.feature.Feature
import org.geotools.api.feature.simple.SimpleFeature
import org.geotools.api.feature.simple.SimpleFeatureType
import org.geotools.api.feature.type.FeatureType
import org.geotools.api.feature.type.GeometryDescriptor
import org.geotools.api.referencing.crs.CoordinateReferenceSystem
import org.geotools.data.simple.SimpleFeatureCollection
import org.geotools.feature.FeatureCollection
import org.geotools.geojson.geom.GeometryJSON
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.locationtech.jts.geom.Geometry

/**
 * Transforms SimpleFeatureCollection to SpatialDataset with feature geometries encoded in GEOJSON format.
 *
 * @param decimals the number of decimals to use when encoding floating point numbers.
 */
fun SimpleFeatureCollection.toSpatialDataset(decimals: Int = 10): SpatialDataset {
    @Suppress("UNCHECKED_CAST")
    return _toSpatialDataset(this as FeatureCollection<FeatureType, Feature>, decimals)
}

/**
 * Transforms abstract SimpleFeatureCollection to SpatialDataset with feature geometries encoded in GEOJSON format.
 *
 * @param decimals the number of decimals to use when encoding floating point numbers.
 */
fun FeatureCollection<FeatureType, Feature>.toSpatialDataset(decimals: Int = 10): SpatialDataset {
    return _toSpatialDataset(this, decimals)
}

private fun _toSpatialDataset(
    featureCollection: FeatureCollection<FeatureType, Feature>,
    decimals: Int
): SpatialDataset {
    val geojson = GeometryJSON(decimals)
    val (data, geometries, crs) = getDataAndGeometries(featureCollection) {
        geojson.toString(it)
    }
    return SpatialDataset.withGEOJSON(data, geometries, crs)
}

private fun getDataAndGeometries(
    featureCollection: FeatureCollection<FeatureType, Feature>,
    geometryToString: (Geometry) -> String
): Triple<Map<String, List<Any?>>, List<String>, String?> {
    require(featureCollection.schema is SimpleFeatureType) {
        "GeoTools: SimpleFeatureType expected but was: ${featureCollection.schema::class.simpleName}"
    }
    val attributeDescriptors = (featureCollection.schema as SimpleFeatureType).attributeDescriptors

    val dataAttributes = attributeDescriptors?.filter { it !is GeometryDescriptor }?.map { it!! } ?: emptyList()
    val geometryAttribute = attributeDescriptors?.find { it is GeometryDescriptor }
        ?: throw IllegalArgumentException("No geometry attribute")

    // In GeoJSON the crs attribute is optional
    val crs: CoordinateReferenceSystem? = (geometryAttribute as GeometryDescriptor).coordinateReferenceSystem

    val data = dataAttributes.associate { it.localName to ArrayList<Any?>() }
    val geometries = ArrayList<String>()

    featureCollection.features().use {
        while (it.hasNext()) {
            val feature = it.next()
            require(feature is SimpleFeature) {
                "GeoTools: SimpleFeature expected but was: ${feature::class.simpleName}"
            }
            val featureGeometry = feature.getAttribute(geometryAttribute.name)
            require(featureGeometry is Geometry) {
                "Not a geometry: [${geometryAttribute.name}] = ${featureGeometry?.javaClass?.simpleName} (feature id: ${feature.id})"
            }
            require(featureGeometry.isValid) { "Invalid geometry, feature id: ${feature.id}" }

            for (dataAttribute in dataAttributes) {
                data[dataAttribute.localName]?.add(feature.getAttribute(dataAttribute.name))
            }
            geometries.add(geometryToString(featureGeometry))
        }
    }
    return Triple(data, geometries, crs?.name?.code)
}