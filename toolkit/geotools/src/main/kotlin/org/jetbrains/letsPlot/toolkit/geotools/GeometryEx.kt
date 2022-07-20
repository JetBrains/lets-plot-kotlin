/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.geotools

import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.geotools.geojson.geom.GeometryJSON
import org.locationtech.jts.geom.Geometry

/**
 * Transforms Geometry to SpatialDataset with feature geometries encoded in GEOJSON format.
 *
 * @param decimals the number of decimals to use when encoding floating point numbers.
 */
fun Geometry.toSpatialDataset(decimals: Int = 10): SpatialDataset {
    require(this.isValid) { "Invalid geometry: ${this.geometryType}" }

    val geoJson = GeometryJSON(decimals).toString(this)
    return SpatialDataset.withGEOJSON(emptyMap(), listOf(geoJson))
}