/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBrowserContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.interact.ggtb

object PanZoom {
    @JvmStatic
    @Suppress("DuplicatedCode")
    fun main(args: Array<String>) {
        ScriptInBrowserContext.eval("'geom_bar()' == 'stat_count'") {
            val xs = List(100) { i -> -2.0 * Math.PI + (4.0 * Math.PI * i / 99) }
            val data = mapOf<String, Any>(
                "x" to xs,
                "y" to xs.map { Math.sin(it) }
            )
            val p = ggplot(data) + ggtb() +
                    geomPoint {
                        x = "x"
                        y = "y"
                    }
            p.show()
        }
    }
}