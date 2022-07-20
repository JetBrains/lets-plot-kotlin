/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

actual object ReflectionPatch {
    actual fun simpleName(o: Any): String? {
        return o::class.simpleName
    }

    actual fun qualifiedName(o: Any): String? {
        return o::class.qualifiedName
    }
}