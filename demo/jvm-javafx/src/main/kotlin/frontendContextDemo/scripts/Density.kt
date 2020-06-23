/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.stat.stat_density2d

object Density {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Density plot") {
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "x" to List(100) { rand.nextGaussian() },
                "y" to List(100) { rand.nextGaussian() }
            )
            val p = lets_plot(data) { x = "x"; y = "y" }

            run {
                val pl = p + geom_density(color = "red", alpha = 0.3, size = 5.0)
                pl.show()
            }

            run {
                val pl = p + geom_point(alpha = .7) + geom_density2d(size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            // Path + density2d stat ==> the same
            run {
                val pl = p + geom_point(alpha = .7) +
                        geom_path(stat = Stat.density2d(), size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            // Path + stat_density2d ==> the same
            run {
                val pl = p + geom_point(alpha = .7) +
                        stat_density2d(size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            run {
                val pl = p + geom_density2df { fill = "..level.." }
                pl.show()
            }

            // Polygon + density2d stat ==> the same
            run {
                val pl = p +
                        geom_polygon(stat = Stat.density2df()) { fill = "..level.." }
                pl.show()
            }
        }
    }
}