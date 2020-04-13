/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.geom.geom_boxplot
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import frontendContextDemo.ScriptInJfxContext
import kotlin.math.abs

object GGBunchDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("GGBunch: Density + Boxplot") {
            val density = lets_plot(densityData()) + geom_density(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
            val boxplot = lets_plot(boxplotData()) { x = "cat"; y = "val" } + geom_boxplot(outlierColor = "red")

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