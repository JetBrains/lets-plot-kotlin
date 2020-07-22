/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_alpha

object ScaleAlpha {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Alpha scale") {
            run {
                val rand = java.util.Random()
                val n = 500
                val data = mapOf<String, Any>(
                    "x" to List(n) { rand.nextGaussian() },
                    "y" to List(n) { rand.nextGaussian() }
                )

                val p = lets_plot(data) { x = "x"; y = "y" } +
                        geom_point(stat = Stat.density2d(contour = false, n = 30)) { alpha = "..density.." } +
                        scale_alpha(range = 0.5 to 1)
                p.show()
            }

            run {
                val df = mapOf<String, Any>(
                    "x" to (1..3).toList(),
                    "y" to (1..3).toList(),
                    "alpha" to listOf(0.5, 1.0, 1.5)
                )
                val p = lets_plot(df) { x = "x"; y = "y"; alpha = "alpha" } +
                        geom_bar(stat = Stat.identity) +
                        scale_alpha(breaks = listOf(0.5, 1.0, 1.5), labels = listOf("a", "b", "c"))
                p.show()
            }
        }
    }
}