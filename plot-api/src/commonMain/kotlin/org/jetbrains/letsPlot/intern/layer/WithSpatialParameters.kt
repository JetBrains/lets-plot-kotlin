/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.spatial.SpatialDataset

/**
 * @property map Spatial dataset for mapping data.
 * @property mapJoin Pair of variable names for joining data with map.
 * @property useCRS Coordinate reference system to use.
 */
interface WithSpatialParameters {
    val map: SpatialDataset?
    val mapJoin: Pair<Any, Any>?
    val useCRS: String?
}