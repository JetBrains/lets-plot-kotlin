/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.frontend

import mu.KotlinLogging

actual object CurrentFrontendContext {
    actual fun display(plotSpecRaw: MutableMap<String, Any>) {
        val kl = KotlinLogging.logger("CurrentFrontendContext")
        kl.error { "'show()' can't be used in Kotlin/JS based web applications." }
    }
}