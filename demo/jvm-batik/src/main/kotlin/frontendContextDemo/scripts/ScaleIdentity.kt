/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomSegment
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.*

object ScaleIdentity {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Identity color scale") {

            val xs = ('a'..'f').toList()
            val data = mapOf("x" to xs)
            val p = letsPlot(data)

            run {
                val colors = listOf("red", "green", "blue", "yellow", "blue", "orange")
                val tiles = geomTile {
                    x = "x"
                    fill = colors
                }
                (p + tiles + scaleFillIdentity()).show()
            }

            run {
                val shapes = (0..5).toList()
                val points = geomPoint(size = 5.0) {
                    x = xs
                    shape = shapes
                }
                (p + points + scaleShapeIdentity()).show()
            }

            run {
                val sizes = listOf(10, 12, 30, 8, 6, 40)
                val points = geomPoint {
                    x = "x"
                    size = sizes
                }
                (p + points + scaleSizeIdentity()).show()
            }

            run {
                val alphas = listOf(0.3, 0.1, 1.0, 0.5, 0.2, 0.8)
                val points = geomPoint(size = 10.0) {
                    x = "x"
                    alpha = alphas
                }
                (p + points + scaleAlphaIdentity()).show()
            }

            run {
                val lineTypes = (0..5).toList()
                val lines = geomSegment(y = 0.0, yend = 1.0, size = 2.0) {
                    x = "x"
                    xend = "x"
                    linetype = lineTypes
                }
                (p + lines + scaleLinetypeIdentity()).show()
            }
        }
    }
}