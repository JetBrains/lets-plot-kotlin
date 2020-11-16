/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.toolkit.geotools

import jetbrains.letsPlot.spatial.SpatialDataset
import org.geotools.geometry.jts.ReferencedEnvelope
import org.locationtech.jts.geom.GeometryFactory

/**
 * Transforms Geometry to ReferencedEnvelope with feature geometries encoded in GEOJSON format.
 *
 * @param decimals the number of decimals to use when encoding floating point numbers.
 */
fun ReferencedEnvelope.toSpatialDataset(decimals: Int = 10): SpatialDataset {
    val crs = this.coordinateReferenceSystem
    require(CRSUtil.isWGS84_2D(crs)) {
        "ReferencedEnvelope must use WGS84 coordinate reference system but was: ${crs.name}."
    }

    val geometry = GeometryFactory().toGeometry(this)!!
    return geometry.toSpatialDataset(decimals)
}