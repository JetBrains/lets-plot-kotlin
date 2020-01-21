/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import jetbrains.letsPlot.element_blank
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.theme
import frontendContextDemo.SwingJfxDemo

object Theme {
    @JvmStatic
    fun main(args: Array<String>) {
        SwingJfxDemo.eval("Theme") {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) +
                    geom_tile(width = 1.0, height = 10.0) { x = "x"; color = "x"; fill = "x" } +
                    theme(
                        legend_position = "none",
                        axis_text_y = element_blank(),
                        axis_ticks_y = element_blank(),
                        axis_title_y = element_blank()
                    )

            p.show()
        }
    }
}
