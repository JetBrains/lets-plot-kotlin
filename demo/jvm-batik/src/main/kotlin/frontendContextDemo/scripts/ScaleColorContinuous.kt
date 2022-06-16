/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.scale.*

object ScaleColorContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Color scale (continuous)", maxCol = 2
        ) {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) + ggsize(600, 200) +
                    geomTile(width = 1.0, height = 10.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            p.show()

            val gradient = scaleFillGradient2(low = "green", mid = "yellow", high = "red") +
                    scaleColorGradient2(low = "green", mid = "yellow", high = "red")
            (p + gradient).show()

            val WB = scaleFillGrey(0, 1) + scaleColorGrey(0, 1)
            (p + WB).show()
            val BW = scaleFillGrey(1, 0) + scaleColorGrey(1, 0)
            (p + BW).show()

            // evenly spaced hues
            (p + scaleFillHue() + scaleColorHue()).show()
            (p + scaleFillHue(c = 20) + scaleColorHue(c = 20)).show()
            (p + scaleFillHue(l = 40) + scaleColorHue(l = 40)).show()
            (p + scaleFillHue(h = 0 to 60) + scaleColorHue(h = 0 to 60)).show()
            (p + scaleFillHue(hstart = 180) + scaleColorHue(hstart = 180)).show()

            val gradientColors = listOf("red", "orange", "yellow", "green", "light_blue", "blue", "magenta")
            (p + scaleFillGradientN(colors = gradientColors) + scaleColorGradientN(colors = gradientColors)).show()
        }
    }
}
