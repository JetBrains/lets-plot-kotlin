/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.geom.geomViolin
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle

object Violin {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Violin plot") {

            run {
                val mpgData = AutoMpg.map()
                val p = ggplot(mpgData) { x = "number of cylinders"; y = "miles per gallon" }
                (p + geomViolin { fill = asDiscrete(variable = "number of cylinders", order = 1) }).show()
                (p + geomViolin(scale = "width", drawQuantiles = listOf(0.25, 0.5, 0.75))).show()
                (p + ggtitle("half violins") +
                        geomViolin(showHalf = -1) {
                            fill = asDiscrete(variable = "number of cylinders", order = 1)
                        } +
                        geomViolin(showHalf = 1, fill = "#ffffb2")
                        ).show()
            }

            // with NaN
            run {
                val data = mapOf(
                    "class" to listOf(0, 0, 0, null, 1, 1, 1, 1),
                    "value" to listOf(0, 0, 2, 2, 1, 1, 3, null)
                )
                (ggplot(data) { x = "class"; y = "value" } + ggtitle("NaNs in data") + geomViolin()).show()
            }

            // Additional grouping
            run {
                val data = mapOf(
                    "class" to listOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'B'),
                    "group" to listOf('x', 'x', 'x', 'y', 'y', 'y', 'x', 'x', 'x', 'x', 'y', 'y'),
                    "value" to listOf(0, 0, 2, 1, 1, 3, 1, 3, 3, 5, 2, 4)
                )
                (ggplot(data) + ggtitle("Additional grouping") +
                        geomViolin(drawQuantiles = listOf(0.25, 0.5, 0.75)) { x = "class"; y = "value"; fill = "group" }
                        ).show()
            }
        }
    }
}