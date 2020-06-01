/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.notebook.jupyter

import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.letsPlot.FrontendContext
import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec

@Suppress("unused")
class KotlinJupyterFrontendContext(private val htmlRenderer: (String) -> Unit) : FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        val html = PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(plotSpecRaw)
        htmlRenderer(html)
    }

    companion object {
        fun getConfigureHtml(version: String): String {
            return PlotHtmlHelper.getDynamicConfigureHtml(PlotHtmlHelper.scriptUrl(version), false)
        }

        fun getHtml(plot: Plot): String {
            return PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(plot.toSpec())
        }

        fun getHtml(plotBunch: GGBunch): String {
            return PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(plotBunch.toSpec())
        }
    }
}
