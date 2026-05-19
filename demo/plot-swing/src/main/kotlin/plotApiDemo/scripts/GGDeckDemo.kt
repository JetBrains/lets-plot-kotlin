/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotApiDemo.scripts

import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggdeck
import org.jetbrains.letsPlot.interact.ggtb
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleYContinuous
import org.jetbrains.letsPlot.themes.elementLine
import org.jetbrains.letsPlot.themes.theme
import org.jetbrains.letsPlot.themes.themeClassic
import plotApiDemo.ScriptInSwingContext

object GGDeckDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInSwingContext.eval(
            "ggdeck()",
            maxCol = 2
        ) {

            val data = randomData(50)

            val line = letsPlot(data) + geomLine(color = "blue") {
                x = "x"
                y = "y1"
            } + labs(y = "Line (left)")


            val point = letsPlot(data) + geomPoint(color = "red") {
                x = "x"
                y = "y2"
            } +
                    labs(y = "Points (right)") +
                    scaleYContinuous(position = "right") +
                    theme(
                        axisTextY = elementLine(color = "blue"),
                        axisTicksY = elementLine(color = "blue"),
                        axisLineY = elementLine(color = "blue"),
                    )

            (ggdeck(
                plots = listOf(line, point),
            ) + themeClassic() + ggtb())
                .show()
        }
    }

    private fun randomData(@Suppress("SameParameterValue") n: Int): Map<String, List<*>> {
        val random = kotlin.random.Random(42)
        val x = (0 until n).toList()

        var cumSum = 0.0
        val y1 = List(n) {
            // Box-Muller transform for normal distribution
            val u1 = random.nextDouble()
            val u2 = random.nextDouble()
            val z0 = kotlin.math.sqrt(-2.0 * kotlin.math.ln(u1)) * kotlin.math.cos(2.0 * kotlin.math.PI * u2)

            cumSum += z0
            cumSum
        }

        val y2 = List(n) { random.nextDouble(100.0, 200.0) }

        return mapOf(
            "x" to x,
            "y1" to y1,
            "y2" to y2,
        )
    }
}