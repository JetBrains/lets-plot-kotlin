/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.frontend

import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.datalore.plot.PlotHtmlHelper.scriptUrl
import jetbrains.letsPlot.FrontendContext
import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec

@Suppress("unused")
class NotebookFrontendContext(
    private val libraryVersion: String,
    private val isolatedFrame: Boolean,
    private val htmlRenderer: (String) -> Unit
) : FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        val html = PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(plotSpecRaw)
        htmlRenderer(html)
    }

    override fun getInfo(): String {
        return "Notebook with ${if (isolatedFrame) "static HTML in output" else "dynamically loaded JS"}. " +
                "Lets-Plot library v.$libraryVersion."
    }

    fun getConfigureHtml(): String {
        return if (isolatedFrame) {
            // Do not pre-load JS library
            ""
        } else {
            PlotHtmlHelper.getDynamicConfigureHtml(scriptUrl(libraryVersion), false)
        }
    }

    fun getHtml(plot: Plot): String {
        return getDisplayHtml(plot.toSpec())
    }

    fun getHtml(plotBunch: GGBunch): String {
        return getDisplayHtml(plotBunch.toSpec())
    }

    private fun getDisplayHtml(rawSpec: MutableMap<String, Any>): String {
        return if (isolatedFrame) {
            """
            ${PlotHtmlHelper.getStaticConfigureHtml(scriptUrl(libraryVersion))}    
            ${PlotHtmlHelper.getStaticDisplayHtmlForRawSpec(rawSpec)}    
            """.trimIndent()
        } else {
            PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(rawSpec)
        }
    }
}
