/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.facet.facet_grid
import jetbrains.letsPlot.geom.geom_histogram
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot

object FacetGrid {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Density plot") {
            val rand = java.util.Random(123)
            val n = 200
            val data = mapOf<String, Any>(
                "cond" to List(n) { "A" } + List(n) { "B" },
                "rating" to List(n) { rand.nextGaussian() } + List(n) { rand.nextGaussian() * 1.5 + 1.5 }
            )

            // Horizontal grid
            run {
                val p = lets_plot(data) { x = "rating" } +
                        geom_histogram(binWidth = .5, color = "black", fill = "white") +
                        facet_grid("cond") + ggtitle("Horizontal grid")
                p.show()
            }

            // Vertical grid
            run {
                val p = lets_plot(data) { x = "rating" } +
                        geom_histogram(binWidth = .5, color = "black", fill = "white") +
                        facet_grid(y = "cond") + ggtitle("Vertical grid")
                p.show()
            }
        }
    }
}