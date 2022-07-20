/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

/**
 * Patching JS compilation error: Unsupported [This reflection API is not supported yet in JavaScript]
 * Kotlin 1.4
 * ToDo: check Kotlin 1.5
 */
expect object ReflectionPatch {
    fun simpleName(o: Any): String?
    fun qualifiedName(o: Any): String?
}