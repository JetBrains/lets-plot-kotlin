/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.frontend

import org.jetbrains.letsPlot.FrontendContext
import org.jetbrains.letsPlot.awt.plot.util.SimplePlotViewerWindow

/**
 * Shows plot in Java Swing Window.
 */
class DefaultSwingFrontendContext private constructor() : FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        SimplePlotViewerWindow(
            "Plot Viewer",
            rawSpec = plotSpecRaw,
            windowSize = null,
            preserveAspectRatio = false,
            repaintDelay = 300
        ).open()
    }

    companion object {
        fun tryCreate(): FrontendContext? {
            return try {
                Class.forName("org.jetbrains.letsPlot.awt.plot.util.SimplePlotViewerWindow")
                // If Ok - create the frontend context.
                DefaultSwingFrontendContext()
            } catch (e: Throwable) {
                when (e) {
                    is ClassNotFoundException,
                    is NoClassDefFoundError -> null

                    else -> throw e
                }
            }
        }
    }
}