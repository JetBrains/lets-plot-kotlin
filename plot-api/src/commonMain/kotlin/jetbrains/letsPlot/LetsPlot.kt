/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.frontend.NotebookFrontendContext
import jetbrains.letsPlot.intern.OptionsMap

object LetsPlot {
    var frontendContext: FrontendContext = createDefaultFrontendContext()

    @Suppress("MemberVisibilityCanBePrivate")
    var apiVersion: String = "Unknown"

    fun setTheme(theme: OptionsMap?) {
        GlobalSettings.setTheme(theme)
    }

    @Suppress("unused")
    fun getInfo() = "Lets-Plot Kotlin API v.$apiVersion. Frontend: ${frontendContext.getInfo()}"

    @Suppress("unused")
    fun setupNotebook(
        jsVersion: String,
        isolatedFrame: Boolean?,
        htmlRenderer: (String) -> Unit
    ): NotebookFrontendContext {
        val isolatedFrameContext: Boolean = isolatedFrame ?: jetbrains.letsPlot.GlobalSettings.isolatedFrameContext
        frontendContext = NotebookFrontendContext(jsVersion, isolatedFrameContext, htmlRenderer)
        return frontendContext as NotebookFrontendContext
    }
}
