/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.*
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_smooth
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

                val p = letsPlot(data) {
                    x = "x"; y = "y"; color = "g"; group = "g"
                } + geom_line()

                println((p + ggtitle("coord_map()") + coord_map()).toSpec())

                (p + ggtitle("coord_fixed()") + coord_fixed()).show()
                (p + ggtitle("coord_fixed(x_lim)") + coord_fixed(xlim = 7 to 17)).show()
                (p + ggtitle("coord_fixed(y_lim)") + coord_fixed(ylim = 7 to 17)).show()
                (p + ggtitle("coord_fixed(x_lim+y_lim)") + coord_fixed(
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
                        theme().legendPosition_none() +
                        geom_point() + geom_smooth(method = "loess")
                (p + ggtitle("loess")).show()
                (p + ggtitle("coord_cartesian(xlim/ylim=[-1,1])") + coord_cartesian(
                    xlim = -1 to 1,
                    ylim = -1 to 1
                )).show()
            }
        }
    }
}