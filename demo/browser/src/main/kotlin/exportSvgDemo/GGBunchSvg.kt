/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package exportSvgDemo

import BrowserDemoUtil
import jetbrains.datalore.plot.PlotSvgExport
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.letsPlot
import kotlin.math.abs

@Suppress("DuplicatedCode")
object GGBunchSvg {
    @JvmStatic
    fun main(args: Array<String>) {
        val density = letsPlot(densityData()) + geomDensity(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
        val boxplot = letsPlot(boxplotData()) { x = "cat"; y = "val" } + geomBoxplot(outlierColor = "red")

        // Create plot spec using lets-plot Kotlin API
        val w = 300
        val h = 250
        val bunch = GGBunch()
        bunch.addPlot(density, 0, 0, w, h)
        bunch.addPlot(boxplot, w + 10, h + 10, w, h)

        val spec = bunch.toSpec()

        // Export: use PlotSvgExport utility to generate SVG.
        val svg = PlotSvgExport.buildSvgImageFromRawSpecs(spec)
        BrowserDemoUtil.openInBrowser(svg)
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