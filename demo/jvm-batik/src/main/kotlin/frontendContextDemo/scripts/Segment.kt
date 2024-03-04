/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.extras.arrow
import org.jetbrains.letsPlot.geom.geomCurve
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomSegment
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.*

object Segment {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("geom_segment/curve") {
            run {
                val rand = java.util.Random()
                val n = 10
                val xList = (0 until n).toList()
                val yList = List(n) { rand.nextInt(10) }
                val data = mapOf<String, Any>(
                    "x" to xList.dropLast(1),
                    "y" to yList.dropLast(1),
                    "xend" to xList.drop(1),
                    "yend" to yList.drop(1),
                )
                val p = letsPlot(data) +
                        geomPoint(size = 12, alpha = 0.6) { x = "x"; y = "y" } +
                        geomSegment(
                            sizeStart = 12, sizeEnd = 12,
                            arrow = arrow(type = "closed", ends = "both", angle = 20)
                        ) {
                            x = "x";
                            y = "y";
                            xend = "xend";
                            yend = "yend";
                        }
                p.show()
            }

            run {
                val data = mapOf(
                    "x" to listOf(-1.0, 0.0, 1.0),
                    "y" to listOf(-1.0, 1.0, -1.0),
                    "shape" to listOf(1.0, 16.0, 21.0),
                    "size" to listOf(1.0, 2.0, 3.0),
                    "stroke" to listOf(1.0, 0.0, 2.0),
                    "x_end" to listOf(0.0, 1.0, -1.0),
                    "y_end" to listOf(1.0, -1.0, -1.0),
                    "size_end" to listOf(2.0, 3.0, 1.0),
                    "stroke_end" to listOf(0.0, 2.0, 1.0)
                )
                val p = letsPlot(data) { x = "x"; y = "y" } +
                        geomPoint(color = "#4575b4", fill = "#abd9e9") { size = "size"; shape = "shape"; stroke = "stroke" } +
                        geomSegment(size = 1.2, alpha = 0.8, arrow = arrow(angle = 15, length = 24, ends = "both", type = "open")) {
                            xend = "x_end"; yend = "y_end";
                            sizeStart = "size"; sizeEnd = "size_end";
                            strokeStart = "stroke"; strokeEnd = "stroke_end"
                        } +
                        geomCurve(size = 1.2, alpha = 0.8, arrow = arrow(angle = 15, length = 24, ends = "both", type = "open"), curvature = -0.2) {
                            xend = "x_end"; yend = "y_end";
                            sizeStart = "size"; sizeEnd = "size_end";
                            strokeStart = "stroke"; strokeEnd = "stroke_end"
                        } +
                        scaleShapeIdentity() +
                        scaleSize(range = 20 to 30, guide = "none") +
                        scaleStroke(range = 0 to 10, guide = "none") +
                        lims(x = -1.5 to 1.5, y = -1.5 to 1.5)
                p.show()
            }
        }
    }
}
