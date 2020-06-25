/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_density2d
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.*
import jetbrains.letsPlot.theme

object ScaleGuide {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Guide of scale") {

            run {
                val xs = ('a'..'f').toList()
                val data = mapOf("x" to xs)
                val colors = listOf("red", "green", "blue", "yellow", "orange", "pink")
                val p = lets_plot(data) + ggsize(600, 200) +
                        geom_tile {
                            x = "x"
                            fill = colors
                        }

                (p + scale_fill_identity(guide = guide_legend(ncol = 2))).show()
                (p + scale_fill_identity(guide = guide_legend(nrow = 2, byRow = true))).show()
                (p + scale_fill_identity(guide = guide_legend(nrow = 2, byRow = false))).show()
            }

            run {
                val rand = java.util.Random()
                val data = mapOf<String, Any>(
                    "x" to List(100) { rand.nextGaussian() },
                    "y" to List(100) { rand.nextGaussian() }
                )
                val p = lets_plot(data) { x = "x"; y = "y" } +
                        theme().legendPosition_bottom() +
                        geom_point(alpha = .7) +
                        geom_density2d(size = 1.0, alpha = .7) { color = "..level.." }

                (p + scale_color_gradient(
                    low = "dark_green", high = "yellow",
                    guide = guide_colorbar(barHeight = 10.0, barWidth = 300.0)
                )).show()
            }
        }
    }
}