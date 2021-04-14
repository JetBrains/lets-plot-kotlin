/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_segment
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*

object ScaleIdentity {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Identity color scale") {

            val xs = ('a'..'f').toList()
            val data = mapOf("x" to xs)
            val p = letsPlot(data)

            run {
                val colors = listOf("red", "green", "blue", "yellow", "blue", "orange")
                val tiles = geom_tile {
                    x = "x"
                    fill = colors
                }
                (p + tiles + scale_fill_identity()).show()
            }

            run {
                val shapes = (0..5).toList()
                val points = geom_point(size = 5.0) {
                    x = xs
                    shape = shapes
                }
                (p + points + scale_shape_identity()).show()
            }

            run {
                val sizes = listOf(10, 12, 30, 8, 6, 40)
                val points = geom_point {
                    x = "x"
                    size = sizes
                }
                (p + points + scale_size_identity()).show()
            }

            run {
                val alphas = listOf(0.3, 0.1, 1.0, 0.5, 0.2, 0.8)
                val points = geom_point(size = 10.0) {
                    x = "x"
                    alpha = alphas
                }
                (p + points + scale_alpha_identity()).show()
            }

            run {
                val lineTypes = (0..5).toList()
                val lines = geom_segment(y = 0.0, yend = 1.0, size = 2.0) {
                    x = "x"
                    xend = "x"
                    linetype = lineTypes
                }
                (p + lines + scale_linetype_identity()).show()
            }
        }
    }
}