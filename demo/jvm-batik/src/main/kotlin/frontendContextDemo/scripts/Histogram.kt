/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geomHistogram
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.stat.statBin

object Histogram {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Histogram") {
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "x" to List(500) { rand.nextGaussian() } + List(500) { rand.nextGaussian() + 1.0 },
                "c" to List(500) { "A" } + List(500) { "B" }
            )

            run {
                val geom = geomHistogram(stat = Stat.bin(binWidth = 1.0, boundary = 0.0), size = 0.0) {
                    x = "x"; fill = "c"
                }
                val p = ggplot(data) + geom
                p.show()
            }

            run {
                val geom = geomHistogram(bins = 3, size = 0.0) {
                    x = "x"; fill = "c"
                }
                (ggplot(data) + geom).show()
            }

            run {
                val geom = geomHistogram(size = 0.0, binWidth = 1.0, boundary = 0.0) {
                    x = "x"; fill = "c"
                }
                val p = ggplot(data) + geom
                p.show()
            }

            run {
                val geom = statBin(size = 0.0, binWidth = 1.0, boundary = 0.0) {
                    x = "x"; fill = "c"
                }
                val p = ggplot(data) + geom
                p.show()
            }
        }
    }
}