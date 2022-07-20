/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.frontend

import jetbrains.datalore.vis.swing.jfx.PlotViewerWindowJfx
import org.jetbrains.letsPlot.FrontendContext

/**
 * Shows plot in Java Swing Window.
 * Uses JavaFx Scene mapping for SVG rendering.
 */
class DefaultSwingJfxFrontendContext private constructor() : FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        PlotViewerWindowJfx(
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
                // Try load JavaFx window class.
                // Requires "lets-plot-jfx-<version>.jar" in classpath.
                PlotViewerWindowJfx("", rawSpec = HashMap())
                // If Ok - create the frontend context.
                DefaultSwingJfxFrontendContext()
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
