/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.scale_alpha

object ScaleAlpha {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Alpha scale") {
            val rand = java.util.Random()
            val n = 500
            val data = mapOf<String, Any>(
                "x" to List(n) { rand.nextGaussian() },
                "y" to List(n) { rand.nextGaussian() }
            )

            val p = lets_plot(data) { x = "x"; y = "y" } +
                    geom_point(stat = Stat.density2d(contour = false, n = 30)) { alpha = "..density.." } +
                    scale_alpha(range = 0.5 to 1)
            p.show()
        }
    }
}