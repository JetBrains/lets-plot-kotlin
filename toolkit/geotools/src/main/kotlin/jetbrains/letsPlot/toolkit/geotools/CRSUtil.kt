/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.toolkit.geotools

import org.opengis.referencing.crs.CoordinateReferenceSystem
import org.opengis.referencing.cs.CoordinateSystem

internal object CRSUtil {
    @Suppress("FunctionName")
    fun isWGS84_2D(crs: CoordinateReferenceSystem): Boolean {
        return crs.coordinateSystem.dimension == 2 &&
                isWGS84Code(crs.name.code)
    }

    @Suppress("FunctionName")
    fun isWGS84_2D(cs: CoordinateSystem): Boolean {
        return cs.dimension == 2 &&
                isWGS84Code(cs.name.code)
    }

    fun isWGS84Code(code: String?): Boolean {
        // matches "GCS_WGS_1984", ""WGS84(DD)
        return code?.contains(Regex(".*WGS[ _]{0,1}(19){0,1}84.*")) ?: false
    }
}