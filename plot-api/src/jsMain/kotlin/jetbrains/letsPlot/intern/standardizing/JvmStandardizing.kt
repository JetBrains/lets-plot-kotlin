/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.standardizing

actual object JvmStandardizing {
    actual fun isJvm(o: Any): Boolean {
        return false
    }

    actual fun standardize(o: Any): Any {
        throw IllegalStateException("Not supported in Kotlin/JS")
    }
}