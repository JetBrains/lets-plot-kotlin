/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geom_histogram
import jetbrains.letsPlot.ggplot
import frontendContextDemo.ScriptInBatikContext

object Histogram {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Histogram") {
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "x" to List(500) { rand.nextGaussian() } + List(500) { rand.nextGaussian() + 1.0 },
                "c" to List(500) { "A" } + List(500) { "B" }
            )

            val geom = geom_histogram(stat = Stat.bin(binWidth = 0.5, boundary = 0.0), alpha = 0.3, size = 0.0) {
                x = "x"; fill = "c"
            }
            val p = ggplot(data) + geom
            p.show()
        }
    }
}