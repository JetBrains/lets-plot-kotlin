/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBrowserContext
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.letsPlot
import kotlin.math.abs

object GGBunchDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBrowserContext.eval("GGBunch: Density + Boxplot") {
            val density = letsPlot(densityData()) + geomDensity(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
            val boxplot = letsPlot(boxplotData()) { x = "cat"; y = "val" } + geomBoxplot(outlierColor = "red")

            // set plot size via `addPlot` func param
            val w = 300
            val h = 250
            var bunch = GGBunch()
            bunch.addPlot(density, 0, 0, w, h)
            bunch.addPlot(boxplot, w + 10, h + 10, w, h)
            bunch.show()

            // set plot size via `ggsize`
            bunch = GGBunch()
            bunch.addPlot(density + ggsize(w, h), 0, 0)
            bunch.addPlot(boxplot + ggsize(w, h), w + 10, h + 10)
            bunch.show()

            // default plot sizes
            bunch = GGBunch()
            bunch.addPlot(density, 0, 0)
            bunch.addPlot(boxplot, w + 10, h + 10)
            bunch.show()
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