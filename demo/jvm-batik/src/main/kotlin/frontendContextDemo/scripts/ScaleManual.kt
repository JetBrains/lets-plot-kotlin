/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_segment
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.*

object ScaleManual {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Manual color scale") {
            val xs = ('a'..'h').toList()
            val data = mapOf("x" to xs)

            val p = lets_plot(data) + ggsize(600, 200)

            run {
                val tiles = geom_tile(width = 1.0, height = 1.0) {
                    x = "x"
                    color = "x"
                    fill = "x"
                }

                val palette = listOf("green", "yellow", "red", "#0072B2", "#D55E00", "#CC79A7")
                val gradient = scale_fill_manual(values = palette) +
                        scale_color_manual(values = palette)
                (p + tiles + gradient).show()
            }

            run {
                val points = geom_point(color = "black", alpha = .3) {
                    x = "x"
                    size = "x"
                }

                val sizes = listOf(10, 20, 5, 2, 8, 50)
                (p + points + scale_size_manual(values = sizes)).show()
            }

            run {
                val points = geom_point(color = "black", alpha = .7, size = 7.0) {
                    x = "x"
                    shape = "x"
                }

                val shapes = (10..30).toList()
                (p + points + scale_shape_manual(values = shapes)).show()
            }

            run {
                val lines = geom_segment(color = "black", y = -1.0, yend = 1.0) {
                    x = "x"
                    xend = "x"
                    linetype = "x"
                }

                val linetypes = listOf(3, 5)
                (p + lines + scale_linetype_manual(values = linetypes)).show()
            }

            run {
                val points = geom_point(color = "black", size = 7.0) {
                    x = "x"
                    alpha = "x"
                }

                val alphas = listOf(.3, 1.0)
                (p + points + scale_alpha_manual(values = alphas)).show()
            }
        }
    }
}
