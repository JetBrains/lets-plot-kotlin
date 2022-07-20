/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

expect object JvmStandardizing {
    fun isJvm(o: Any): Boolean
    fun isDateTimeJvm(o: Any): Boolean
    fun standardize(o: Any): Any
}