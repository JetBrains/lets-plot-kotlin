/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.settings

import java.util.*

internal actual object Env {
    actual fun get(name: String): String? = System.getenv(name)

    actual fun getBool(name: String): Boolean? {
        val value = get(name)
        return when {
            value.isNullOrBlank() -> false
            value.trim().lowercase(Locale.getDefault()) in listOf("true", "1", "t", "y", "yes") -> true
            value.trim().lowercase(Locale.getDefault()) in listOf("false", "0", "f", "n", "no") -> false
            else -> {
                throw IllegalArgumentException("Can't convert str to boolean : [$name] : $value")
            }
        }
    }
}
