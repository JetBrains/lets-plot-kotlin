/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomStep
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.tooltips.layerTooltips
import org.jetbrains.letsPlot.tooltips.tooltipsNone

object Step {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("geom_step") {
            val data = mapOf<String, Any>(
                "x" to listOf(1,2,3,4,5,6,7),
                "y" to listOf(1,3,6,7,8,9,12)
            )
            val p = letsPlot(data) { x = "x"; y = "y" } +
                    geomStep(tooltips = layerTooltips().line("y=^y")) +
                    geomStep(direction = "vh", linetype = 3, tooltips = tooltipsNone)
            p.show()
        }
    }
}