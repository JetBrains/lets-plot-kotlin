/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.stat.statSmooth
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
                val p = letsPlot(data) { x = "x"; y = "y" } +
                        theme().legendPositionNone() +
                        geomPoint(shape = 21, fill = "yellow", color = "blue") +
                        geomSmooth(method = "lm", deg = 5, size = 1.2)

                p.show()
            }

            run {
                // the same
                val p = letsPlot(data) { x = "x"; y = "y" } +
                        theme().legendPositionNone() +
                        geomPoint(shape = 21, fill = "yellow", color = "blue") +
                        statSmooth(method = "lm", deg = 5, size = 1.2)

                p.show()
            }
        }
    }
}