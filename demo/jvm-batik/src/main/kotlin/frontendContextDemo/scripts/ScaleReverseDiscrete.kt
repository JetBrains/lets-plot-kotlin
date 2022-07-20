/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleXDiscreteReversed
import org.jetbrains.letsPlot.scale.scaleYDiscreteReversed

object ScaleReverseDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale reverse (discrete)") {

            val dat = mapOf(
                "x" to ('a'..'h'),
                "y" to ('a'..'h'),
                "v" to (0..7)
            )

            val p = letsPlot(dat) + geomTile(showLegend = false) { x = "x"; y = "y"; fill = "v" }
            p.show()

            (p + scaleXDiscreteReversed() + ggtitle("scale_x_discrete_reversed()")).show()
            (p + scaleYDiscreteReversed() + ggtitle("scale_y_discrete_reversed()")).show()
        }
    }
}