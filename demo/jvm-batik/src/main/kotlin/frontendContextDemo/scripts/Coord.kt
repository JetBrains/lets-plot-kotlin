/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.coord_fixed
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot

object Coord {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("coord x/y limits") {
            val data = mapOf<String, Any>(
                "x" to listOf(0, 5, 10, 15, 20, 25),
                "y" to listOf(0, 5, 10, 15, 20, 25),
                "g" to listOf('a', 'a', 'b', 'b', 'c', 'c')
            )

            val p = lets_plot(data) { x = "x"; y = "y"; color = "g"; group = "g" } + geom_line()

            (p + ggtitle("coord_fixed()") + coord_fixed()).show()
            (p + ggtitle("coord_fixed(x_lim)") + coord_fixed(xlim = 7 to 17)).show()
            (p + ggtitle("coord_fixed(y_lim)") + coord_fixed(ylim = 7 to 17)).show()
            (p + ggtitle("coord_fixed(x_lim+y_lim)") + coord_fixed(
                xlim = -1 to 7,
                ylim = -1 to 15
            )).show()
        }
    }
}