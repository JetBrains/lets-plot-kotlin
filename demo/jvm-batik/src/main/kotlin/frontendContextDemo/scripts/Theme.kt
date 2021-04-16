/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.theme

object Theme {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Theme") {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) +
                    geomTile(width = 1.0, height = 10.0) { x = "x"; color = "x"; fill = "x" }


            (p + theme()
                .legendPositionNone()
                .axisTextYBlank()
                .axisTicksYBlank()
                .axisTitleYBlank())
                .show()

            // compose themes --> the same
            (p + theme()
                .legendPositionNone() +
                    theme()
                        .axisTextYBlank()
                        .axisTicksYBlank()
                        .axisTitleYBlank())
                .show()

        }
    }
}
