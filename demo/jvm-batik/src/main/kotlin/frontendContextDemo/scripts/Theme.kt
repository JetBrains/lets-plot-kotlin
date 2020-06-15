/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.theme

object Theme {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Theme") {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) +
                    geom_tile(width = 1.0, height = 10.0) { x = "x"; color = "x"; fill = "x" } +
                    theme()
                        .legendPosition_none()
                        .axisTextY_blank()
                        .axisTicksY_blank()
                        .axisTitleY_blank()

            p.show()
        }
    }
}
