/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.settings.GlobalSettings
import org.jetbrains.letsPlot.intern.settings.createDefaultFrontendContext

object LetsPlot {
    var frontendContext: FrontendContext = createDefaultFrontendContext()

    var theme: Any? = null

    internal fun getThemeAsFeatureList(): List<Feature> {
        val theme = theme ?: return emptyList()
        return when (theme) {
            is OptionsMap -> listOfNotNull(theme)
            is FeatureList -> theme.elements
            is Feature -> listOfNotNull(theme)
            else -> throw IllegalArgumentException("Only `theme(...)`, `themeXxx()`, `flavorXxx()`, or a sum of them are supported")
        }
    }

    internal fun getThemeOptionMaps(): List<OptionsMap> {
        return getThemeAsFeatureList().filterIsInstance<OptionsMap>()
    }

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
