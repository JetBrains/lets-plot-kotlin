/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

object GlobalSettings {
    var frontendContext: FrontendContext = object : FrontendContext {
        override fun display(plotSpecRaw: MutableMap<String, Any>) {
            throw IllegalStateException("Frontend context is not defined")
        }
    }
}