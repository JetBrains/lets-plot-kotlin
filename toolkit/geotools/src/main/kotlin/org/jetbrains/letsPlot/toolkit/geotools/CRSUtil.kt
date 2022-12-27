/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.geotools

import org.jetbrains.letsPlot.spatial.CRSCode
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
        if (code == null) {
            return false
        }

        return CRSCode.isWGS84Code(code)
    }
}