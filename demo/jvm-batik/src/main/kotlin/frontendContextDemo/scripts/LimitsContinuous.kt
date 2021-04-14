/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_hline
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_vline
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*
import java.util.*

@Suppress("DuplicatedCode")
object LimitsContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("labs(title=\" Sine function\", x=\"Sine argument (ms)\", y=\"Sine value (mm)\")") {
            // Generate random data-points

            val n = 500
            val rand = Random()
            val dat = mapOf<String, Any>(
                "x" to List(n) { rand.nextGaussian() },
                "y" to List(n) { rand.nextGaussian() }
            )

            val p = letsPlot(dat) +
                    geom_point(color = "black", alpha = .3) { x = "x"; y = "y" }
            p.show()

            (p + lims(x = -1 to 1, y = -1 to 1) +
                    geom_vline(linetype = 3) { xintercept = Pair(-1, 1) } +
                    geom_hline(linetype = 3) { yintercept = listOf(-1, 1) }
                    ).show()

            (p + lims(x = -1 to null, y = null to 1) +
                    geom_vline(linetype = 3, xintercept = -1) +
                    geom_hline(linetype = 3, yintercept = 1)
                    ).show()

            (p + xlim(-10 to 10) + ylim(-10 to 10) +
                    geom_vline(linetype = 3) { xintercept = Pair(-10, 10) } +
                    geom_hline(linetype = 3) { yintercept = listOf(-10, 10) }
                    ).show()

            // scale continuous limits

            (p + scale_x_continuous(limits = -1 to 1) +
                    geom_vline(linetype = 3) { xintercept = -1 to 1 }
                    ).show()

            (p + scale_x_continuous(limits = -1 to 1, expand = listOf(.0, .5)) +
                    geom_vline(linetype = 3) { xintercept = -1 to 1 }
                    ).show()

            (p + scale_x_continuous(limits = -1 to 1, expand = listOf(.0, .5)) +
                    geom_vline(linetype = 3) { xintercept = -1 to 1 } +
                    scale_y_continuous(limits = -2 to 2, expand = listOf(.0, 1)) +
                    geom_hline(linetype = 3) { yintercept = -2 to 2 }
                    ).show()
        }
    }
}