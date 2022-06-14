/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.arrow
import jetbrains.letsPlot.geom.geomSegment
import jetbrains.letsPlot.letsPlot

object Segment {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("geom_segment") {
            val rand = java.util.Random()
            val n = 20
            val xList = (0 until n).toList()
            val yList = List(n) { rand.nextInt(10) }
            val data = mapOf<String, Any>(
                "x" to xList.dropLast(1),
                "y" to yList.dropLast(1),
                "xend" to xList.drop(1),
                "yend" to yList.drop(1),
            )
            val p = letsPlot(data) + geomSegment(
                arrow = arrow(type = "closed", ends = "both", angle = 20)
            ) {
                x = "x";
                y = "y";
                xend = "xend";
                yend = "yend";
            }
            p.show()
        }
    }
}
