/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.spatial

object CRSCode {
    // matches "GCS_WGS_1984", ""WGS84(DD)
    fun isWGS84Code(code: String) = code.contains(Regex(".*WGS[ _]{0,1}(19){0,1}84.*"))
}