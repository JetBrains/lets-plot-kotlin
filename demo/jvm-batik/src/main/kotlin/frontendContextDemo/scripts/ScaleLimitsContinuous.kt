/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_color_continuous
import jetbrains.letsPlot.scale.scale_size
import jetbrains.letsPlot.scale.scale_x_continuous
import jetbrains.letsPlot.theme

object ScaleLimitsContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale limits (continuous)", maxCol = 1) {
            val dat0 = mapOf<String, Any>(
                "x" to (0..4).map { it }
            )
            val dat1 = mapOf<String, Any>(
                "x" to (5..9).map { it }
            )

            val settings = theme()
                .legendPosition_none()
                .axisLine_blank()
                .axisTextY_blank()
                .axisTicksY_blank().axisTextY_blank()
                .axisTitleY_blank() +
                    ggsize(500, 100)
            val p0 = lets_plot(dat0) + geom_point { x = "x"; size = "x"; color = "x" } + settings
            val p1 = lets_plot(dat1) + geom_point { x = "x"; size = "x"; color = "x" } + settings

            // Each plot uses different size limits
            run {
                val b = GGBunch()
                    .addPlot(p0, 0, 0)
                    .addPlot(p1, 0, 100)
                b.show()
            }

            // Both plots use the same size limits
            run {
                val scales = scale_size(limits = 0 to 9) +
                        scale_color_continuous(limits = 0 to 9) +
                        scale_x_continuous(limits = 0 to 9)
                val b = GGBunch()
                    .addPlot(p0 + scales, 0, 0)
                    .addPlot(p1 + scales, 0, 100)
                b.show()
            }
        }
    }
}