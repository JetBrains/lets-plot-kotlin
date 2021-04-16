/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleShape
import jetbrains.letsPlot.theme

object ScaleShape {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale for shapes") {
            val xs = (0..5).toList()
            val data = mapOf("x" to xs, "s" to xs)
            val p = letsPlot(data) +
                    ggsize(600, 200) +
                    theme().legendPositionNone() +
                    geomPoint(size = 7.0) {
                        x = "x"
                        shape = "s"
                    }

            (p + scaleShape(solid = true)).show()
            (p + scaleShape(solid = false)).show()
        }
    }
}