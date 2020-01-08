/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotDemo.scripts

import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.scale.scale_fill_gradient2
import plotDemo.SwingJfxDemoFrontendContext

object ColorScaleContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = SwingJfxDemoFrontendContext("Color Scale")
        GlobalSettings.frontendContext = ctx

        val xs = (-64..64).toList()
        val data = mapOf("x" to xs)

        val p = ggplot(data) +
                geom_tile(width = 1.0, height = 10.0) {
                    x = "x"
                    color = "x"
                    fill = "x"
                }


        p.show()

        val gradient = scale_fill_gradient2(low = "green", mid = "yellow", high = "red")
        (p + gradient).show()

        // ====================
        ctx.showAll()
    }
}
