/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.frontend.NotebookFrontendContext
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.settings.GlobalSettings
import jetbrains.letsPlot.intern.settings.createDefaultFrontendContext

object LetsPlot {
    var frontendContext: FrontendContext = createDefaultFrontendContext()

    var theme: OptionsMap? by GlobalSettings::theme

    @Suppress("MemberVisibilityCanBePrivate")
    var apiVersion: String = "Unknown"

    @Suppress("unused")
    fun getInfo() = "Lets-Plot Kotlin API v.$apiVersion. Frontend: ${frontendContext.getInfo()}"

    @Suppress("unused")
    fun setupNotebook(
        jsVersion: String,
        isolatedFrame: Boolean?,
        htmlRenderer: (String) -> Unit
    ): NotebookFrontendContext {
        val isolatedFrameContext: Boolean = isolatedFrame ?: GlobalSettings.isolatedFrameContext
        frontendContext = NotebookFrontendContext(jsVersion, isolatedFrameContext, htmlRenderer)
        return frontendContext as NotebookFrontendContext
    }
}
