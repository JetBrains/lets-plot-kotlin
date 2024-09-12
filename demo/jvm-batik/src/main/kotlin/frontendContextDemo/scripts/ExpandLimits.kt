/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.expandLimits

object ExpandLimits {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("expandLimits()") {

            val mpgData = AutoMpg.map()

            run {
                val p = ggplot(mpgData) {
                    x = "miles per gallon"
                    y = "vehicle weight (lbs.)"
                }


                // Default
                (p + geomPoint() + ggtitle("Default")).show()

                // Expand Lower Limit Along the x-axis
                (p + geomPoint() + expandLimits(x = 0) + ggtitle("expandLimits(x = 0)")).show()

                // Expand Limits Along the y-axis
                (p + geomPoint() + expandLimits(
                    y = listOf(
                        1000,
                        9000
                    )
                ) + ggtitle("expandLimits(y = listOf(1000, 9000))")).show()

                // Expand Lower Limits Along Both Axes
                (p + geomPoint() + expandLimits(x = 0, y = 0) + ggtitle("expandLimits(x = 0, y = 0)")).show()
            }

            run {
                // Expanding Color-scale Limits
                val p = ggplot(mpgData) {
                    x = "miles per gallon"
                    y = "vehicle weight (lbs.)"
                    color = "number of cylinders"
                }


                // Default
                (p + geomPoint() + ggtitle("Default with color")).show()

                // Expand the color-scale limits.
                (p + geomPoint() + expandLimits(color = 2..10) + ggtitle("expandLimits(color = 2..10)")).show()
            }
        }
    }
}