/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.ThemeOptionsUtil
import org.jetbrains.letsPlot.intern.settings.GlobalSettings
import org.jetbrains.letsPlot.intern.settings.createDefaultFrontendContext

object LetsPlot {
    var frontendContext: FrontendContext = createDefaultFrontendContext()

    var theme: Feature? = null  // either null or OptionsMap
        set(value) {
            field = when (value) {
                null -> null
                is OptionsMap -> value
                is FeatureList -> {
                    val optionsMap = value.elements.map { it as? OptionsMap ?: error("theme: unsupported feature $it") }
                    optionsMap.forEach {
                        require(it.kind == Option.Plot.THEME) {
                            "theme: wrong options type, expected `${Option.Plot.THEME}` but was `${it.kind}`"
                        }
                    }
                    ThemeOptionsUtil.toSpec(optionsMap)?.let { mergedOptions ->
                        OptionsMap(Option.Plot.THEME, mergedOptions)
                    }
                }
                else -> throw IllegalArgumentException("Only `theme(...)`, `themeXxx()`, `flavorXxx()`, or a sum of them are supported")
            }
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
