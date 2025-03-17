/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.ggbunch
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.elementRect
import org.jetbrains.letsPlot.themes.theme
import org.jetbrains.letsPlot.themes.themeBW
import kotlin.math.abs

object GGBunchDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("ggbunch(): Density + Boxplot", maxCol = 2) {
            val theme = themeBW() + theme(plotBackground = elementRect(size = 1))
            val density = letsPlot(densityData()) +
                    geomDensity(color = "red", alpha = 0.3, size = 5.0) { x = "x" } + theme
            val boxplot = letsPlot(boxplotData()) { x = "cat"; y = "val" } +
                    geomBoxplot(outlierColor = "red") + theme

            // Plots in opposite corners
            ggbunch(
                listOf(density, boxplot),
                regions = listOf(
                    listOf(0, 0, 0.49, 0.49),
                    listOf(0.51, 0.51, 0.49, 0.49),
                )
            ).show()

            // Overlapped plots
            ggbunch(
                listOf(density, boxplot),
                regions = listOf(
                    listOf(0, 0, 0.6, 0.6),
                    listOf(0.4, 0.4, 0.6, 0.6),
                )
            ).show()
        }
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