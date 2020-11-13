/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_x_discrete_reversed
import jetbrains.letsPlot.scale.scale_y_discrete_reversed

object ScaleReverseDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale reverse (discrete)") {

            val dat = mapOf(
                "x" to ('a'..'h'),
                "y" to ('a'..'h'),
                "v" to (0..7)
            )

            val p = lets_plot(dat) + geom_tile(showLegend = false) { x = "x"; y = "y"; fill = "v" }
            p.show()

            (p + scale_x_discrete_reversed() + ggtitle("scale_x_discrete_reversed()")).show()
            (p + scale_y_discrete_reversed() + ggtitle("scale_y_discrete_reversed()")).show()
        }
    }
}