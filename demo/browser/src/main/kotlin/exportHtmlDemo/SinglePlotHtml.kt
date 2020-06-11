/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package exportHtmlDemo

import BrowserDemoUtil
import jetbrains.datalore.plot.PlotHtmlExport
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.intern.toSpec

@Suppress("DuplicatedCode")
object SinglePlotHtml {
    @JvmStatic
    fun main(args: Array<String>) {
        val data = mapOf<String, Any>(
            "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
            "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
        )
        val p = ggplot(data) +
                geom_bar {
                    x = "cat1"
                    fill = "cat2"
                } +
                ggtitle("Bar Chart HTML")

        val spec = p.toSpec()

        // Export: use PlotHtmlExport utility to generate dynamic HTML (optionally in iframe).
        val html = PlotHtmlExport.buildHtmlFromRawSpecs(spec, iFrame = true)
        BrowserDemoUtil.openInBrowser(html)
    }
}