/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_shape
import jetbrains.letsPlot.theme

object ScaleShape {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale for shapes") {
            val xs = (0..5).toList()
            val data = mapOf("x" to xs, "s" to xs)
            val p = lets_plot(data) +
                    ggsize(600, 200) +
                    theme().legendPosition_none() +
                    geom_point(size = 7.0) {
                        x = "x"
                        shape = "s"
                    }

            (p + scale_shape(solid = true)).show()
            (p + scale_shape(solid = false)).show()
        }
    }
}