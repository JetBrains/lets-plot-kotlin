/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.scale.scaleFillGradient2

object ScaleColorContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Color Scale") {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) +
                    geomTile(width = 1.0, height = 10.0) {
                        x = "x"
                        color = "x"
                        fill = "x"
                    }

            p.show()

            val gradient = scaleFillGradient2(low = "green", mid = "yellow", high = "red")
            (p + gradient).show()
        }
    }
}
