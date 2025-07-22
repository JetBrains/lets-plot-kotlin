/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

expect object StandardizingTestJvmValues {
    fun getTestValues(): List<Any>
    fun getExpectedValues(): List<Any>
}