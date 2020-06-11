/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.label.xlab
import jetbrains.letsPlot.label.ylab
import jetbrains.letsPlot.lets_plot
import kotlin.math.PI
import kotlin.math.sin

@Suppress("DuplicatedCode")
object Labs {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("labs(title=\" Sine function\", x=\"Sine argument (ms)\", y=\"Sine value (mm)\")") {
            val dat = mapOf<String, Any>(
                "x" to (0..100).map { it * 2 * PI / 100 },
                "y" to (0..100).map { sin(it * 2 * PI / 100) }
            )

            run {
                val p = lets_plot(dat) +
                        geom_point { x = "x"; y = "y"; color = "y" } +
                        labs(
                            title = "Sine function",
                            x = "Sine argument (ms)",
                            y = "Sine value (mm)",
                            color = "Sine value (mm)"
                        )
                p.show()
            }

            run {
                val p = lets_plot(dat) +
                        geom_point { x = "x"; y = "y" } +
                        xlab("Sine argument (ms)") + ylab("Sine value (mm)")
                p.show()
            }
        }
    }
}