/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.coord.coordFixed
import org.jetbrains.letsPlot.geom.geomABLine
import org.jetbrains.letsPlot.geom.geomLollipop
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot

object Lollipop {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Lollipop plot") {
            run {
                val data = mapOf(
                    "x" to listOf(0, 1, 2, 3),
                    "y" to listOf(1, 3, -1, 2),
                    "g" to listOf('a', 'a', 'b', 'a')
                )
                (letsPlot(data) + geomLollipop { x = "x"; y = "y"; color = "g" } + ggtitle("Default lollipop")).show()
            }

            run {
                val data = mapOf(
                    "x" to listOf(0, 1, 2, 3),
                    "y" to listOf(3, 5, -2, 7)
                )

                val p = letsPlot(data) { x = "x"; y = "y" } +
                        coordFixed(ratio = 1) +
                        geomABLine(slope = 1, intercept = 1, orientation = "y") +
                        geomLollipop(
                            size = 4, stroke = 5, shape = 21, linewidth = 1, color = "blue",
                            fill = "red", linetype = "dotted", alpha = 0.5, slope = 1, intercept = 1,
                            dir = "s", orientation = "y"
                        ) +
                        ggtitle("Perpendicular to the line")
                p.show()
            }
        }
    }
}