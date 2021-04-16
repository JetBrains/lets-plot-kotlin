/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.*
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.label.ggtitle

object Coord {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("coord x/y limits") {
            run {
                val data = mapOf<String, Any>(
                    "x" to listOf(0, 5, 10, 15, 20, 25),
                    "y" to listOf(0, 5, 10, 15, 20, 25),
                    "g" to listOf('a', 'a', 'b', 'b', 'c', 'c')
                )

                val p = letsPlot(data) { x = "x"; y = "y"; color = "g"; group = "g" } + geomLine()

                println((p + ggtitle("coord_map()") + coordMap()).toSpec())

                (p + ggtitle("coord_fixed()") + coordFixed()).show()
                (p + ggtitle("coord_fixed(x_lim)") + coordFixed(xlim = 7 to 17)).show()
                (p + ggtitle("coord_fixed(y_lim)") + coordFixed(ylim = 7 to 17)).show()
                (p + ggtitle("coord_fixed(x_lim+y_lim)") + coordFixed(
                    xlim = -1 to 7,
                    ylim = -1 to 15
                )).show()
            }

            run {
                val n = 150
                val rand = java.util.Random()
                val data = mapOf<String, Any>(
                    "x" to List(n) { rand.nextGaussian() },
                    "y" to List(n) { rand.nextGaussian() },
                    "g" to List(50) { "A" } + List(50) { "B" } + List(50) { "C" }
                )

                val p = letsPlot(data) { x = "x"; y = "y" } +
                        theme().legendPositionNone() +
                        geomPoint() + geomSmooth(method = "loess")
                (p + ggtitle("loess")).show()
                (p + ggtitle("coord_cartesian(xlim/ylim=[-1,1])") + coordCartesian(
                    xlim = -1 to 1,
                    ylim = -1 to 1
                )).show()
            }
        }
    }
}