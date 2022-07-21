/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleColorDiscrete
import org.jetbrains.letsPlot.scale.scaleXDiscrete
import org.jetbrains.letsPlot.scale.xlim
import org.jetbrains.letsPlot.themes.elementBlank

object ScaleLimitsDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale limits (discrete)", maxCol = 1) {

            val dat = mapOf(
                "x" to ('a'..'h')
            )

            val settings = org.jetbrains.letsPlot.themes.theme(
                axisLine = elementBlank(),
                axisTitleY = elementBlank(),
                axisTextY = elementBlank(),
                axisTicksY = elementBlank()
            ).legendPositionBottom() + ggsize(500, 150)

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