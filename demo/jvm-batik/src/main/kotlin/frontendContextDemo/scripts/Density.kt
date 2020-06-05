/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.lets_plot

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
            val density = geom_density(color = "red", size = 5.0) { x = "x" }
            val p0 = lets_plot(data) + density
            p0.show()

            // Weighted density plot
            val densityW = geom_density(color = "red", size = 5.0) { x = "x"; weight = "w" }
            val p1 = lets_plot(data) + densityW
            p1.show()
        }
    }
}