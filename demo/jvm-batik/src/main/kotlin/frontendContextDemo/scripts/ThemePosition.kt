/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.theme

object ThemePosition {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Theme position") {
            val xs = (0..5).toList()
            val ys = (0..5).toList()
            val cs = ('a'..'f').toList()
            val data = mapOf("x" to xs, "y" to ys, "c" to cs)

            val p = ggplot(data) + ggsize(500, 200) +
                    geom_point { x = "x"; y = "y"; color = "c" }

            p.show()

            (p + theme()
                .legendPosition_bottom()
                    ).show()

            (p + theme()
                .legendPosition(1, 1)
                .legendJustification(1, .9)
                .legendDirection_horizontal()
                    ).show()

            (p + theme()
                .legendPosition(0.5, 0.5)
                .legendJustification_center()
                .legendDirection_horizontal()
                    ).show()

            (p + theme()
                .legendPosition(0, 0)
                .legendJustification(0, 0)
                .legendDirection_horizontal()
                    ).show()

            (p + theme()
                .legendPosition_left()
                    ).show()
        }
    }
}
