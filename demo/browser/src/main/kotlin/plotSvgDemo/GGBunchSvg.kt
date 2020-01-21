/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotSvgDemo

import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.geom.geom_boxplot
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.lets_plot
import kotlin.math.abs

@Suppress("DuplicatedCode")
object GGBunchSvg {
    @JvmStatic
    fun main(args: Array<String>) {
        val density = lets_plot(densityData()) + geom_density(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
        val boxplot = lets_plot(boxplotData()) { x = "cat"; y = "val" } + geom_boxplot(outlierColor = "red")

        // Create plot spec using lets-plot Kotlin API
        val w = 300
        val h = 250
        val bunch = GGBunch()
        bunch.addPlot(density, 0, 0, w, h)
        bunch.addPlot(boxplot, w + 10, h + 10, w, h)

        val spec = bunch.toSpec()
        PlotSvgDemoUtil.show(
            "GGBunch: a separate SVG image is created for each plot in bunch",
            listOf(spec),
            null
        )
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