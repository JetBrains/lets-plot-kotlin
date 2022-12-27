/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.spatial

object CRSCode {
    fun checkCRS(code: String) {
        require (isWGS84Code(code)) {
            "Geometry must use WGS84 coordinate reference system but was: $code."
        }
    }

    // matches "GCS_WGS_1984", ""WGS84(DD)
    fun isWGS84Code(code: String) = code.contains(Regex(".*WGS[ _]{0,1}(19){0,1}84.*"))
}