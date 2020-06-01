/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.geom.geom_errorbar
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.lets_plot

object ErrorBar {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("ErrorBar") {
            val data = mapOf<String, Any>(
                "supp" to listOf("OJ", "OJ", "OJ", "VC", "VC", "VC"),
                "dose" to listOf(0.5, 1.0, 1.5, 0.5, 1.0, 1.5),
                "len" to listOf(13.23, 21.7, 27.06, 7.00, 17.77, 25.14),
                "min" to listOf(10.83, 20.8, 24.0, 5.24, 15.26, 24.35),
                "max" to listOf(15.63, 24.6, 28.11, 10.72, 18.28, 27.93)
            )

            val geom = geom_errorbar(
                position = Pos.position_dodge(width = 0.1),
                color = "black", width = 0.1
            ) {
                ymin = "min"; ymax = "max"; group = "supp"
            } +
                    geom_line(position = Pos.position_dodge(width = 0.1)) +
                    geom_point(position = Pos.position_dodge(width = 0.1), size = 2.0)

            val p = lets_plot(data) { x = "dose"; y = "len"; color = "supp" } + geom
            p.show()
        }
    }
}