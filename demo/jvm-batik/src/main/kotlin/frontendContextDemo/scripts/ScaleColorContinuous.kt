/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.scale.scale_color_gradient2
import jetbrains.letsPlot.scale.scale_color_grey
import jetbrains.letsPlot.scale.scale_fill_gradient2
import jetbrains.letsPlot.scale.scale_fill_grey

object ScaleColorContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Color scale (continuous)") {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) + ggsize(600, 200) +
                    geom_tile(width = 1.0, height = 10.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            p.show()

            val gradient = scale_fill_gradient2(low = "green", mid = "yellow", high = "red") +
                    scale_color_gradient2(low = "green", mid = "yellow", high = "red")
            (p + gradient).show()

            val WB = scale_fill_grey(0, 1) + scale_color_grey(0, 1)
            (p + WB).show()
            val BW = scale_fill_grey(1, 0) + scale_color_grey(1, 0)
            (p + BW).show()
        }
    }
}
