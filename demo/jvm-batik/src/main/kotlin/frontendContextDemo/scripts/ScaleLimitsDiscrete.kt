/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_fill_discrete
import jetbrains.letsPlot.scale.scale_x_datetime
import jetbrains.letsPlot.theme

object ScaleLimitsDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale limits (discrete)") {

            val dat = mapOf(
                "x" to ('a'..'h')
            )

            val settings = theme()
                .axisLine_blank()
                .axisTitleY_blank().axisTextY_blank().axisTicksY_blank()
                .legendPosition_bottom() +
                    ggsize(500, 150)

            val p = lets_plot(dat) + geom_tile { x = "x"; fill = "x" } + settings
            p.show()

            val limits = ('a'..'d')
            (p + scale_fill_discrete(limits=limits.toList(), naValue = Color.WHITE)).show()
        }
    }
}