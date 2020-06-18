/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geom_freqpoly
import jetbrains.letsPlot.geom.geom_histogram
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.ggplot

object Freqpoly {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Freqpoly") {
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "x" to List(500) { rand.nextGaussian() } + List(500) { rand.nextGaussian() + 1.0 },
                "c" to List(500) { "A" } + List(500) { "B" }
            )

            run {
                val geom = geom_freqpoly(binWidth = 1.0) {
                    x = "x"; color = "c"
                }
                val p = ggplot(data) + geom
                p.show()
            }

            run {
                // line + bin stat ==> Same
                val geom = geom_line(stat = Stat.bin(binWidth = 1.0)) {
                    x = "x"; color = "c"
                }
                val p = ggplot(data) + geom
                p.show()
            }

            run {
                // line + bin stat ==> Same
                val stat = Stat.bin(binWidth = 1.0)
                val geom =
                    geom_histogram(stat = stat, position = Pos.dodge, alpha = .3, size = 0.0) { x = "x"; fill = "c" } +
                            geom_line(stat = stat) { x = "x"; color = "c" }
                val p = ggplot(data) + geom
                p.show()
            }

        }
    }
}