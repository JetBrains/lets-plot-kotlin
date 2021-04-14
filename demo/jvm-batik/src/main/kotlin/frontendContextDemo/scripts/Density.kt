/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geomDensity
import jetbrains.letsPlot.geom.geom_area
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.stat.stat_density

object Density {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Density plot") {
            val rand = java.util.Random()
            val n = 200
            val xs = List(n) { rand.nextGaussian() }
            val data = mapOf<String, Any>(
                "x" to xs,
                "w" to xs.map { if (it < 0.0) 2.0 else 0.5 }
            )

            // Basic plot
            run {
                val p = letsPlot(data) + geomDensity(color = "red", size = 5.0) { x = "x" }
                p.show()
            }

            // Weighted density plot
            run {
                val p = letsPlot(data) +
                        geomDensity(color = "red", size = 5.0) { x = "x"; weight = "w" }
                p.show()
            }

            // stat_density : the sane
            run {
                val p = letsPlot(data) +
                        stat_density(color = "red", size = 5.0) { x = "x"; weight = "w" }
                p.show()
            }

            // Area + density stat ==> the sane
            run {
                val p = letsPlot(data) +
                        geom_area(
                            stat = Stat.density { x = "x"; weight = "w" },
                            color = "red",
                            fill = "green",
                            alpha = .3,
                            size = 5.0
                        )
                p.show()
            }
        }
    }
}