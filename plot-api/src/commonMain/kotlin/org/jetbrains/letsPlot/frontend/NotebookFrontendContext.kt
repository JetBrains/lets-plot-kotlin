/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.frontend

import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.datalore.plot.PlotHtmlHelper.scriptUrl
import org.jetbrains.letsPlot.FrontendContext
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.toSpec

@Suppress("unused")
class NotebookFrontendContext(
    private val jsVersion: String,
    private val isolatedFrame: Boolean,
    private val htmlRenderer: (String) -> Unit
) : FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        val html = PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(plotSpecRaw)
        htmlRenderer(html)
    }

    override fun getInfo(): String {
        return "Notebook with ${if (isolatedFrame) "static HTML in output" else "dynamically loaded JS"}. " +
                "Lets-Plot JS v.$jsVersion."
    }

    fun getConfigureHtml(): String {
        return if (isolatedFrame) {
            // Do not pre-load JS library
            ""
        } else {
            PlotHtmlHelper.getDynamicConfigureHtml(scriptUrl(jsVersion), false)
        }
    }

    fun getHtml(plot: Plot): String {
        return getDisplayHtml(plot.toSpec())
    }

    fun getHtml(figure: SubPlotsFigure): String {
        return getDisplayHtml(figure.toSpec())
    }

    fun getHtml(plotBunch: GGBunch): String {
        return getDisplayHtml(plotBunch.toSpec())
    }

    // Used by alternative kotlin lets-plot API
    // https://github.com/nikitinas/lets-plot-dsl.git
    fun getDisplayHtml(rawSpec: MutableMap<String, Any>): String {
        return if (isolatedFrame) {
            """
            ${PlotHtmlHelper.getStaticConfigureHtml(scriptUrl(jsVersion))}    
            ${PlotHtmlHelper.getStaticDisplayHtmlForRawSpec(rawSpec)}    
            """.trimIndent()
        } else {
            PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(rawSpec)
        }
    }
}
