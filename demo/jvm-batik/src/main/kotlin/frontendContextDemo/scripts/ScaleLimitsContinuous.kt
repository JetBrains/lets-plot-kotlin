/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleColorContinuous
import org.jetbrains.letsPlot.scale.scaleSize
import org.jetbrains.letsPlot.scale.scaleXContinuous
import org.jetbrains.letsPlot.themes.elementBlank
import org.jetbrains.letsPlot.themes.theme

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

            val settings = theme(
                axisLine = elementBlank(),
                axisTicksY = elementBlank(),
                axisTextY = elementBlank(),
                axisTitleY = elementBlank()
            ).legendPositionNone() + ggsize(500, 100)

            val p0 = letsPlot(dat0) + geomPoint { x = "x"; size = "x"; color = "x" } + settings
            val p1 = letsPlot(dat1) + geomPoint { x = "x"; size = "x"; color = "x" } + settings

            // Each plot uses different size limits
            run {
                val b = GGBunch()
                    .addPlot(p0, 0, 0)
                    .addPlot(p1, 0, 100)
                b.show()
            }

            // Both plots use the same size limits
            run {
                val scales = scaleSize(limits = 0 to 9) +
                        scaleColorContinuous(limits = 0 to 9) +
                        scaleXContinuous(limits = 0 to 9)
                val b = GGBunch()
                    .addPlot(p0 + scales, 0, 0)
                    .addPlot(p1 + scales, 0, 100)
                b.show()
            }
        }
    }
}