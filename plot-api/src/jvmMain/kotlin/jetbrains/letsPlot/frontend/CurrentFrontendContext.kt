/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.frontend

import jetbrains.letsPlot.LetsPlot

actual object CurrentFrontendContext {
    actual fun display(plotSpecRaw: MutableMap<String, Any>) {
        LetsPlot.frontendContext.display(plotSpecRaw)
    }
}