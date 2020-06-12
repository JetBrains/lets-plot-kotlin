/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.position_dodge
import jetbrains.letsPlot.geom.geom_crossbar
import jetbrains.letsPlot.lets_plot

object CrossBar {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("CrossBar") {
            val data = mapOf<String, Any>(
                "supp" to listOf("OJ", "OJ", "OJ", "VC", "VC", "VC"),
                "dose" to listOf(0.5, 1.0, 1.5, 0.5, 1.0, 1.5),
                "len" to listOf(13.23, 21.7, 27.06, 7.00, 17.77, 25.14),
                "min" to listOf(10.00, 20.8, 24.0, 5.24, 15.26, 24.35),
                "max" to listOf(15.00, 24.6, 28.11, 10.72, 18.28, 27.93)
            )

            val geom = geom_crossbar(
                width = 0.3,
                size = 2.0,
                alpha = 0.5,
                fatten = 1.0,
                position = position_dodge(width = 0.33)
            ) {
                ymin = "min"; ymax = "max"; middle = "len"
            }

            val p = lets_plot(data) { x = "dose"; fill = "supp" } + geom
            p.show()
        }
    }
}