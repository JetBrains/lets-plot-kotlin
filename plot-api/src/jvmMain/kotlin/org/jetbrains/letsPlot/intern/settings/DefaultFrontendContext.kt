/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.settings

import org.jetbrains.letsPlot.FrontendContext
import org.jetbrains.letsPlot.frontend.DefaultSwingBatikFrontendContext
import org.jetbrains.letsPlot.frontend.DefaultSwingJfxFrontendContext

actual fun createDefaultFrontendContext(): FrontendContext {
    return DefaultSwingBatikFrontendContext.tryCreate()
        ?: DefaultSwingJfxFrontendContext.tryCreate()
        ?: object : FrontendContext {
            override fun display(plotSpecRaw: MutableMap<String, Any>) {
                throw IllegalStateException(
                    """
                                    
                                    The frontend context is not defined.
                                    To define the frontend context please select one of the following options:
                                    a. Add "lets-plot-batik-<version>.jar" to your classpath.   
                                    b. Add "lets-plot-jfx-<version>.jar" to your classpath.   
                                    c. Specify the frontend context explicitly: "LetsPlot.frontendContext = ..."
                                       
                                    """.trimIndent()
                )
            }
        }
}
