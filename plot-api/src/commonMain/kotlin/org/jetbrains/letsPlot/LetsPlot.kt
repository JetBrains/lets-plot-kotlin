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
                is FeatureList -> {
                    val optionsMapList = value.elements.map(::toThemeOptionsMap)
                    ThemeOptionsUtil.toSpec(optionsMapList)?.let { mergedOptions ->
                        OptionsMap(Option.Plot.THEME, mergedOptions)
                    }
                }

                else -> toThemeOptionsMap(value)
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

    private fun toThemeOptionsMap(feature: Feature): OptionsMap {
        require(feature is OptionsMap && feature.kind == Option.Plot.THEME) {
            "'theme' expected but was: $feature"
        }

        return feature
    }
}
