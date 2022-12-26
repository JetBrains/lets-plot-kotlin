/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.geotools

import org.geotools.geometry.jts.ReferencedEnvelope
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.locationtech.jts.geom.GeometryFactory

/**
 * Transforms Geometry to ReferencedEnvelope with feature geometries encoded in GEOJSON format.
 *
 * @param decimals the number of decimals to use when encoding floating point numbers.
 */
fun ReferencedEnvelope.toSpatialDataset(decimals: Int = 10): SpatialDataset {

    val geometry = GeometryFactory().toGeometry(this)!!
    return geometry.toSpatialDataset(decimals)
}