/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.stat.statDensity2D
import jetbrains.letsPlot.stat.statDensity2DFilled

object Density {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Density plot") {
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "x" to List(100) { rand.nextGaussian() },
                "y" to List(100) { rand.nextGaussian() }
            )
            val p = letsPlot(data) { x = "x"; y = "y" }

            run {
                val pl = p + geomDensity(color = "red", alpha = 0.3, size = 5.0)
                pl.show()
            }

            run {
                val pl = p + geomPoint(alpha = .7) + geomDensity2D(size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            // Path + density2d stat ==> the same
            run {
                val pl = p + geomPoint(alpha = .7) +
                        geomPath(stat = Stat.density2D(), size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            // Path + stat_density2d ==> the same
            run {
                val pl = p + geomPoint(alpha = .7) +
                        statDensity2D(size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            run {
                val pl = p + geomDensity2DFilled { fill = "..level.." }
                pl.show()
            }

            // Polygon + density2df stat ==> the same
            run {
                val pl = p +
                        geomPolygon(stat = Stat.density2DFilled()) { fill = "..level.." }
                pl.show()
            }

            // stat_density2df ==> the same
            run {
                val pl = p + statDensity2DFilled { fill = "..level.." }
                pl.show()
            }
        }
    }
}