/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.geom.geom_density2d
import jetbrains.letsPlot.geom.geom_density2df
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

            fun density() {
                val density = geom_density(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
                val p = lets_plot(data) + density
                p.show()
            }

            fun density2d() {
                val density2d = geom_density2d { x = "x"; y = "y"; }
                val p = lets_plot(data) + density2d
                p.show()
            }

            fun density2df() {
                val density2df = geom_density2df { x = "x"; y = "y"; fill = "..level.."}
                val p = lets_plot(data) + density2df
                p.show()
            }

            density()
            density2d()
            density2df()
        }
    }
}