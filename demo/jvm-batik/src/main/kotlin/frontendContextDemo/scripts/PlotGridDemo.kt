/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_boxplot
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.gggrid
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import kotlin.math.abs

object PlotGridDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("gggrid()") {
            val density = lets_plot(densityData()) + geom_density(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
            val boxplot = lets_plot(boxplotData()) { x = "cat"; y = "val" } + geom_boxplot(outlierColor = "red")

            val plots = listOf(
                density + ggsize(200, 100),
                boxplot + ggsize(300, 200),
                density + ggsize(500, 500),
                boxplot + ggsize(150, 150),
                density + ggsize(250, 100),
                boxplot + ggsize(350, 150),
            )
            gggrid(plots, 3, 400, 300).show()
            gggrid(plots, 3, 400, 300, fit = true).show()
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