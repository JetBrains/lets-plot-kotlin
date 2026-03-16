/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.settings

internal actual object Env {
    actual fun get(name: String): String? = null

    actual fun getBool(name: String): Boolean? = null
}
