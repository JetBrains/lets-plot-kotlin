/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.coord.coordFlip
import org.jetbrains.letsPlot.geom.geomAreaRidges
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle

object AreaRidges {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Area ridges") {

            run {
                val data = mapOf(
                    "x" to listOf(null, 0, 1, 2, 3, 4, 1, 2, 3, 4),
                    "y" to listOf(0, 0, 0, 0, 0, null, 1, 1, 1, 1),
                    "h" to listOf(0.5, 0.3, 1.0, 0.4, 0.6, 0.5, 0.3, 0.4, 0.2, 0.3)
                )
                val p = ggplot(data) { x = "x"; y = "y"; height = "h" } +
                        geomAreaRidges(stat = Stat.identity) +
                        ggtitle("Basic demo")
                p.show()
            }

            run {
                val data = mapOf(
                    "x" to listOf(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3),
                                 "y" to listOf(0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2),
                                 "h" to listOf(0.1, 0.2, 0.1, 0.1, 0.1, 0.1, 0.2, 0.1, 0.1, 0.1, 0.2, 0.1),
                                 "g" to listOf('A', 'A', 'B', 'B', 'C', 'C', 'A', 'A', 'B', 'B', 'C', 'C')
                )
                val p = ggplot(data) { x = "x"; y = "y"; height = "h"; fill = "g" } +
                        geomAreaRidges(stat = Stat.identity, color = "black") +
                        ggtitle("Additional grouping")
                p.show()
            }

            run {
                val data = mapOf(
                    "x" to listOf(0, 1, 2, 3, 1, 2, 3, 4),
                    "y" to listOf(0, 0, 0, 0, 0.5, 0.5, 0.5, 0.5),
                    "h" to listOf(0.6, 2.0, 0.8, 1.2, 0.6, 0.8, 0.4, 0.6)
                )
                val p = ggplot(data) { x = "x"; y = "y"; height = "h" } +
                        geomAreaRidges(stat = Stat.identity, scale = 0.5) +
                        coordFlip() +
                        ggtitle("Flip coordinates")
                p.show()
            }

            run {
                val data = mapOf(
                    "x" to listOf(0, 1, 2, 3, 4, 0, 1, 2, 3, 4),
                    "y" to listOf(-1.0, -1.0, -1.0, -1.0, -1.0, -0.5, -0.5, -0.5, -0.5, -0.5),
                    "h" to listOf(1.0, -0.5, 0.5, -1.0, 1.0, 1.0, -1.0, 0.5, -0.5, 1.0)
                )
                val p = ggplot(data) { x = "x"; y = "y"; height = "h" } +
                        geomAreaRidges(stat = Stat.identity, minHeight = -0.9, color = "black", fill = "red") +
                        ggtitle("Negative height")
                p.show()
            }

            run {
                val data = mapOf(
                    "x" to listOf(1, 2, 0, 1, 2, 3),
                    "y" to listOf(1.2, 1.2, 1.1, 1.1, 1.4, 1.4)
                )
                val p = ggplot(data) { x = "x"; y = "y" } +
                        geomAreaRidges(scale = 2, tailsCutoff = 3) +
                        ggtitle("With density ridges stat")
                p.show()
              }

            run {
                val p = ggplot(Iris.map()) {
                    x = "sepal length (cm)"
                    y = "target"
                    fill = "..quantile.."
                } +
                        geomAreaRidges(quantiles = listOf(0.1, 0.25, 0.5, 0.75, 0.9), color = "black") +
                        ggtitle("Quantiles")
                p.show()
            }
        }
    }
}