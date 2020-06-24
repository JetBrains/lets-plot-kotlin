/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.scale.scale_color_discrete
import jetbrains.letsPlot.scale.scale_fill_discrete

object ScaleColorDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Color scale (discrete)") {
            val xs = ('a'..'g')
            val data = mapOf("x" to xs)

            val p = ggplot(data) + ggsize(600, 200) +
                    geom_tile(width = 1.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            (p + scale_color_discrete() + scale_fill_discrete()).show()

            // change the range of hue in palette
            val hue = (0 to 60)
            (p + scale_color_discrete(h = hue) + scale_fill_discrete(h = hue)).show()

            // change Chroma
            (p + scale_color_discrete(h = hue, c = 90) + scale_fill_discrete(h = hue, c = 90)).show()

            // change Chroma
            (p + scale_color_discrete(h = hue, c = 20) + scale_fill_discrete(h = hue, c = 20)).show()

            // change Luminance
            (p + scale_color_discrete(h = hue, l = 70) + scale_fill_discrete(h = hue, l = 70)).show()
        }
    }
}
