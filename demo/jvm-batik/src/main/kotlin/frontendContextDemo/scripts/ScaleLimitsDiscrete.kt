/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleColorDiscrete
import jetbrains.letsPlot.scale.scaleXDiscrete
import jetbrains.letsPlot.scale.xlim
import jetbrains.letsPlot.theme

object ScaleLimitsDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale limits (discrete)", maxCol = 1) {

            val dat = mapOf(
                "x" to ('a'..'h')
            )

            val settings = theme()
                .axisLineBlank()
                .axisTitleYBlank().axisTextYBlank().axisTicksYBlank()
                .legendPositionBottom() +
                    ggsize(500, 150)

            val p = letsPlot(dat) +
                    geomPoint(size = 10.0) { x = "x"; color = "x" } +
                    settings
            p.show()

            val limits = ('a'..'d').toList()
            (p + scaleColorDiscrete(limits = limits, naValue = Color.WHITE) +
                    ggtitle("scale_color_discrete(limits = limits)")).show()

            (p + scaleXDiscrete(limits = limits) +
                    ggtitle("scale_x_discrete(limits = limits)")).show()

            // `xlim` is the other way to do the same
            (p + xlim(limits) +
                    ggtitle("xlim(limits)")).show()
        }
    }
}