/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package exportHtmlDemo

import BrowserDemoUtil
import org.jetbrains.letsPlot.core.util.PlotHtmlExport
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper
import org.jetbrains.letsPlot.export.VersionChecker
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.ggbunch
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.elementRect
import org.jetbrains.letsPlot.themes.theme
import org.jetbrains.letsPlot.themes.themeBW
import kotlin.math.abs

@Suppress("DuplicatedCode")
object GGBunchHtml {
    @JvmStatic
    fun main(args: Array<String>) {
        val theme = themeBW() + theme(plotBackground = elementRect(size = 1))
        val density = letsPlot(densityData()) +
                geomDensity(color = "red", alpha = 0.3, size = 5.0) { x = "x" } + theme
        val boxplot = letsPlot(boxplotData()) { x = "cat"; y = "val" } +
                geomBoxplot(outlierColor = "red") + theme

        // Plots in opposite corners
        val bunch = ggbunch(
            listOf(density, boxplot),
            regions = listOf(
                listOf(0, 0, 0.49, 0.49),
                listOf(0.51, 0.51, 0.49, 0.49),
            )
        )

        val spec = bunch.toSpec()

        // Export: use PlotHtmlExport utility to generate dynamic HTML (optionally in iframe).
        val html = PlotHtmlExport.buildHtmlFromRawSpecs(
            spec,
            iFrame = true,
            scriptUrl = PlotHtmlHelper.scriptUrl(VersionChecker.letsPlotJsVersion)
        )
        BrowserDemoUtil.openInBrowser(html)
    }

    private fun densityData(): Map<*, *> {
        val rand = java.util.Random()
        return mapOf<String, Any>(
            "x" to List(500) { rand.nextGaussian() }
        )
    }

    private fun boxplotData(): Map<*, *> {
        val categories = listOf("A", "B", "C", "D", "E")

        val n = 500
        val rand = java.util.Random()
        return mapOf<String, Any>(
            "val" to List(n) { rand.nextGaussian() },
            "cat" to List(n) { categories[abs(rand.nextInt()).rem(categories.size)] }
        )
    }
}