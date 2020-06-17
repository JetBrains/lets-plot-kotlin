/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.geom.geom_density2d
import jetbrains.letsPlot.geom.geom_density2df
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.lets_plot

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

            fun density() {
                val pl = p + geom_density(color = "red", alpha = 0.3, size = 5.0)
                pl.show()
            }

            fun pointsWithDensity2d() {
                val pl = p + geom_point(alpha = .7) + geom_density2d(size = 1.0, alpha = .7) { color = "..level.." }
                pl.show()
            }

            fun density2df() {
                val pl = p + geom_density2df { fill = "..level.." }
                pl.show()
            }

            density()
            pointsWithDensity2d()
            density2df()
        }
    }
}