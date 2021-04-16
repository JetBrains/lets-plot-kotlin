/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomDensity2D
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*
import jetbrains.letsPlot.theme

object ScaleGuide {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Guide of scale", maxCol = 2) {

            run {
                val xs = ('a'..'f').toList()
                val data = mapOf("x" to xs)
                val colors = listOf("red", "green", "blue", "yellow", "orange", "pink")
                val p = letsPlot(data) + ggsize(600, 200) +
                        geomTile {
                            x = "x"
                            fill = colors
                        }

                (p + scaleFillIdentity(guide = "none")).show()
                (p + scaleFillIdentity(guide = guideLegend(ncol = 2))).show()
                (p + scaleFillIdentity(guide = guideLegend(nrow = 2, byRow = true))).show()
                (p + scaleFillIdentity(guide = guideLegend(nrow = 2, byRow = false))).show()

                // Use 'guides' function
                (letsPlot(data) + ggsize(600, 200) +
                        ggtitle("Use 'guides' function.") +
                        geomTile {
                            x = "x"
                            fill = colors
                            alpha = "x"
                        } +
                        guides(alpha = "none", fill = guideLegend(nrow = 2))).show()
            }

            run {
                val rand = java.util.Random()
                val data = mapOf<String, Any>(
                    "x" to List(100) { rand.nextGaussian() },
                    "y" to List(100) { rand.nextGaussian() }
                )
                val p = letsPlot(data) { x = "x"; y = "y" } +
                        theme().legendPositionBottom() +
                        geomPoint(alpha = .7) +
                        geomDensity2D(size = 1.0, alpha = .7) { color = "..level.." }

                (p + scaleColorGradient(
                    low = "dark_green", high = "yellow",
                    guide = guideColorbar(barWidth = 300, barHeight = 10)
                )).show()

                (p + scaleColorGradient(
                    low = "dark_green", high = "yellow",
                    guide = "legend"
                )).show()
            }
        }
    }
}