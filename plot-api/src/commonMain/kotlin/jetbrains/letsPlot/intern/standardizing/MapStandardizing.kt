/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.standardizing

internal object MapStandardizing {
    fun standardize(map: Map<*, *>): Map<String, Any> {
        return map
            .filter { it.value != null }
            .map { it.key.toString() to Standardizing.standardizeValue(it.value) as Any }.toMap()
    }
}