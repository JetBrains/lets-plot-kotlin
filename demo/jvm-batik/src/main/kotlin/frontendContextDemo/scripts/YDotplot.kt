/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.coordFlip
import org.jetbrains.letsPlot.geom.geomViolin
import org.jetbrains.letsPlot.geom.geomYDotplot
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot

object YDotplot {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Y-Dotplot") {
            val irisData = Iris.map()

            val p = ggplot(irisData) { x = "target"; y = "sepal length (cm)" }

            // Basic
            (p + geomYDotplot() + ggtitle("Default")).show()


            // method = "histodot"
            (p + geomViolin() + geomYDotplot(method = "histodot") + ggtitle("method = \"histodot\"")).show()

            // flip
            (p + geomYDotplot(binWidth = 0.2, dotSize = 0.2) + coordFlip() +
                    ggtitle("binWidth = 0.2, dotSize = 0.2, coord flip")).show()

            // parameters
            (p + geomViolin() + geomYDotplot(
                stackDir = "right", stackRatio = 0.5, dotSize = 2, color = "#f03b20", fill = "#ffeda0"
            ) + ggtitle("stackDir = \"right\", stackRatio = 0.5, dotSize = 2")).show()

            // Grouping without stackgroups
            run {
                val data = mapOf(
                    "class" to listOf('A', 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'B'),
                    "group" to listOf('x', 'x', 'x', 'x', 'y', 'y', 'y', 'x', 'x', 'x', 'x', 'y', 'y'),
                    "value" to listOf(0, 0, 0, 1, 1, 1, 2, 1, 2, 2, 3, 2, 3),
                )

                (letsPlot(data) { x = "class"; y = "value"; fill = "group" } +
                        geomYDotplot(method = "histodot", binWidth = 0.25) +
                        ggtitle("Grouping without stackgroups")).show()
            }


            // stat = "identity"

            run {
                val data = mapOf(
                    "x" to listOf(null, 'C', 'C', 'C', 'A', 'A', 'B', 'B'),
                    "y" to listOf(0, null, 2, 3, 0, 1, 1, 2),
                    "count" to listOf(2, 1, null, 3, 3, 1, 2, 0),
                    "binwidth" to listOf(0.25, 0.25, 0.25, null, 0.25, 0.25, 0.25, 0.25),
                )

                (letsPlot(data) + geomYDotplot(stat = Stat.identity) {
                    x = "x"
                    y = "y"
                    stackSize = "count"
                    binWidth = "binwidth"
                } + ggtitle("stat = Stat.identity")).show()
            }
        }
    }
}