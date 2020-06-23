/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geom_point

import jetbrains.letsPlot.geom.geom_smooth
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.stat.stat_smooth
import jetbrains.letsPlot.theme

object Smooth {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Smooth") {
            val n = 150
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "x" to List(n) { rand.nextGaussian() },
                "y" to List(n) { rand.nextGaussian() }
            )
            run {
                val p = lets_plot(data) { x = "x"; y = "y" } +
                        theme().legendPosition_none() +
                        geom_point(shape = 21, fill = "yellow", color = "blue") +
                        geom_smooth(method = "lm", deg = 5, size = 1.2)

                p.show()
            }

            run {
                // the same
                val p = lets_plot(data) { x = "x"; y = "y" } +
                        theme().legendPosition_none() +
                        geom_point(shape = 21, fill = "yellow", color = "blue") +
                        stat_smooth(method = "lm", deg = 5, size = 1.2)

                p.show()
            }
        }
    }
}