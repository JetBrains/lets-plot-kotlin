/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.frontend

import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.letsPlot.Figure
import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import kotlinx.browser.document
import kotlinx.dom.createElement
import org.w3c.dom.*

object JsFrontendUtil {
    fun createPlotDiv(plot: Figure): HTMLDivElement {
        val spec = when (plot) {
            is Plot -> plot.toSpec()
            else -> (plot as GGBunch).toSpec()
        }
        val html = PlotHtmlHelper.getStaticDisplayHtmlForRawSpec(spec)
        val divElement = document.createElement("div")
        setInnerHtml(divElement, html)
        return divElement as HTMLDivElement
    }

    fun setInnerHtml(element: Element, html: String) {
        element.innerHTML = html

        // Force browser to execute scripts in the "html".
        val allScripts = element.querySelectorAll("script")
        for (i in 0 until allScripts.length) {
            val oldScript = allScripts.get(i) as Element
            val newScript = document.createElement("script") {
                this is HTMLScriptElement
                oldScript.attributes.asList().forEach { attr ->
                    this.setAttribute(attr.name, attr.value)
                }

                this.appendChild(document.createTextNode(oldScript.innerHTML))
            }

            (oldScript.parentNode as Element).replaceChild(newScript, oldScript)
        }
    }
}