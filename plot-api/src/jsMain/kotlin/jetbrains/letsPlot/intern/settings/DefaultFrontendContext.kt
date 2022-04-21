/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.settings

import jetbrains.letsPlot.FrontendContext

actual fun createDefaultFrontendContext() = object : FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        throw IllegalStateException(
            """
                            
                            The frontend context is not defined.
                            Specify the frontend context explicitly: "LetsPlot.frontendContext = ..."
                               
                            """.trimIndent()
        )
    }
}
