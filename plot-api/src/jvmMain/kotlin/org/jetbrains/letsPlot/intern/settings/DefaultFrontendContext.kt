/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.settings

import org.jetbrains.letsPlot.FrontendContext
import org.jetbrains.letsPlot.frontend.DefaultSwingFrontendContext

actual fun createDefaultFrontendContext(): FrontendContext {
    return DefaultSwingFrontendContext.tryCreate()
        ?: object : FrontendContext {
            override fun display(plotSpecRaw: MutableMap<String, Any>) {
                throw IllegalStateException(
                    """
                                    
                                    The frontend context is not defined.
                                    To define the frontend context please select one of the following options:
                                    a. Add "org.jetbrains.lets-plot:platf-awt:<version>" to your dependencies.   
                                    b. Specify the frontend context explicitly: "LetsPlot.frontendContext = ..."
                                       
                                    """.trimIndent()
                )
            }
        }
}
