/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.scale.scaleColorBrewer
import org.jetbrains.letsPlot.scale.scaleFillBrewer

object ScaleColorBrewer {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Color Brewer Scale", maxCol = 2
        ) {
            val data = mapOf("x" to (-64..64))

            val p = ggplot(data) + ggsize(600, 200) +
                    geomTile(width = 1.0, height = 10.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            (p + scaleFillBrewer() + scaleColorBrewer()).show()

            // change direction
            (p + scaleFillBrewer(direction = -1) + scaleColorBrewer(direction = -1)).show()

            // Diverging palette type
            (p + scaleFillBrewer(type = "div") + scaleColorBrewer(type = "div")).show()

            // Qualitative
            (p + scaleFillBrewer(type = "qual") + scaleColorBrewer(type = "qual")).show()

            // Qualitative + Paired
            (p + scaleFillBrewer(type = "qual", palette = "Paired") +
                    scaleColorBrewer(type = "qual", palette = "Paired")).show()
        }
    }
}