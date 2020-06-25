/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_color_discrete
import jetbrains.letsPlot.scale.scale_x_discrete
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

            val p = lets_plot(dat) +
// there is NPE on tiles                    geom_tile { x = "x"; fill = "x" } +
                    geom_point(size = 10.0) { x = "x"; color = "x" } +
                    settings
            p.show()

            val limits = ('a'..'d').toList()
            (p + scale_color_discrete(limits = limits, naValue = Color.WHITE)).show()

            (p + scale_color_discrete(limits = limits) + scale_x_discrete(limits = limits)).show()
//            println((p + scale_fill_discrete(limits = limits) + scale_x_discrete(limits = limits)).toSpec())

//            // bug
//            val dd = mapOf(
//                "x" to ('a'..'c')
//            )
//            println((lets_plot(dd) + geom_tile { x = "x" } + scale_x_discrete(limits = listOf('b'))).toSpec())
        }
    }
}