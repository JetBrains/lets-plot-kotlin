/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.scale.scale_color_brewer
import jetbrains.letsPlot.scale.scale_fill_brewer

object ScaleColorBrewer {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Color Brewer Scale", maxCol = 2
        ) {
            val data = mapOf("x" to (-64..64))

            val p = ggplot(data) + ggsize(600, 200) +
                    geom_tile(width = 1.0, height = 10.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            (p + scale_fill_brewer() + scale_color_brewer()).show()

            // change direction
            (p + scale_fill_brewer(direction = -1) + scale_color_brewer(direction = -1)).show()

            // Diverging palette type
            (p + scale_fill_brewer(type = "div") + scale_color_brewer(type = "div")).show()

            // Qualitative
            (p + scale_fill_brewer(type = "qual") + scale_color_brewer(type = "qual")).show()

            // Qualitative + Paired
            (p + scale_fill_brewer(type = "qual", palette = "Paired") +
                    scale_color_brewer(type = "qual", palette = "Paired")).show()
        }
    }
}