/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_color_manual
import jetbrains.letsPlot.scale.scale_fill_manual

object ScaleManual {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Manual color scale") {
            val xs = ('a'..'h').toList()
            val data = mapOf("x" to xs)

            val p = lets_plot(data) + ggsize(600, 200) +
                    geom_tile(width = 1.0, height = 1.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            val palette = listOf("green", "yellow", "red", "#0072B2", "#D55E00", "#CC79A7")
            val gradient = scale_fill_manual(values = palette) +
                    scale_color_manual(values = palette)
            (p + gradient).show()
        }
    }
}
