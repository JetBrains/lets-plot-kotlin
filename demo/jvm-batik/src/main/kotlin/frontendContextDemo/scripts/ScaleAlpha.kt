/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleAlpha

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

                val p = letsPlot(data) { x = "x"; y = "y" } +
                        geomPoint(stat = Stat.density2D(contour = false, n = 30)) { alpha = "..density.." } +
                        scaleAlpha(range = 0.5 to 1)
                p.show()
            }

            run {
                val df = mapOf<String, Any>(
                    "x" to (1..3).toList(),
                    "y" to (1..3).toList(),
                    "alpha" to listOf(0.5, 1.0, 1.5)
                )
                val p = letsPlot(df) { x = "x"; y = "y"; alpha = "alpha" } +
                        geomBar(stat = Stat.identity) +
                        scaleAlpha(breaks = listOf(0.5, 1.0, 1.5), labels = listOf("a", "b", "c"))
                p.show()
            }
        }
    }
}