/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

/**
 * Reflection API is not supported yet in JavaScript
 * Kotlin 1.4
 * ToDo: check Kotlin 1.5
 */
@Suppress("RedundantNullableReturnType")
actual object ReflectionPatch {
    actual fun simpleName(o: Any): String? {
        return "<JavaScript type>"
    }

    actual fun qualifiedName(o: Any): String? {
        return "<JavaScript type>"
    }
}