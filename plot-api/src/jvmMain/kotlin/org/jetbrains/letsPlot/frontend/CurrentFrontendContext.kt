/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.frontend

import org.jetbrains.letsPlot.LetsPlot

actual object CurrentFrontendContext {
    actual fun display(plotSpecRaw: MutableMap<String, Any>) {
        LetsPlot.frontendContext.display(plotSpecRaw)
    }
}