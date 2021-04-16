/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomSegment
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*

object ScaleManual {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Manual color scale", maxCol = 2) {
            val xs = ('a'..'h').toList()
            val data = mapOf("x" to xs)

            val p = letsPlot(data) + ggsize(600, 200)

            run {
                val tiles = geomTile(width = 1.0, height = 1.0) {
                    x = "x"
                    color = "x"
                    fill = "x"
                }

                val palette = listOf("green", "yellow", "red", "#0072B2", "#D55E00", "#CC79A7")
                val gradient = scaleFillManual(values = palette) +
                        scaleColorManual(values = palette)
                (p + tiles + gradient).show()
            }

            run {
                val points = geomPoint(color = "black", alpha = .3) {
                    x = "x"
                    size = "x"
                }

                val sizes = listOf(10, 20, 5, 2, 8, 50)
                (p + points + scaleSizeManual(values = sizes)).show()
            }

            run {
                val points = geomPoint(color = "black", alpha = .7, size = 7.0) {
                    x = "x"
                    shape = "x"
                }

                val shapes = (10..30).toList()
                (p + points + scaleShapeManual(values = shapes)).show()
            }

            run {
                val lines = geomSegment(color = "black", y = -1.0, yend = 1.0) {
                    x = "x"
                    xend = "x"
                    linetype = "x"
                }

                val linetypes = listOf(3, 5)
                (p + lines + scaleLinetypeManual(values = linetypes)).show()
            }

            run {
                val points = geomPoint(color = "black", size = 7.0) {
                    x = "x"
                    alpha = "x"
                }

                val alphas = listOf(.3, 1.0)
                (p + points + scaleAlphaManual(values = alphas)).show()
            }
        }
    }
}
