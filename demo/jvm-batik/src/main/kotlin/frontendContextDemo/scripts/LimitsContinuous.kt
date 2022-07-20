/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomHLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomVLine
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.*
import org.jetbrains.letsPlot.scale.*
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
                    geomPoint(color = "black", alpha = .3) { x = "x"; y = "y" }
            p.show()

            (p + lims(x = -1 to 1, y = -1 to 1) +
                    geomVLine(linetype = 3) { xintercept = Pair(-1, 1) } +
                    geomHLine(linetype = 3) { yintercept = listOf(-1, 1) }
                    ).show()

            (p + lims(x = -1 to null, y = null to 1) +
                    geomVLine(linetype = 3, xintercept = -1) +
                    geomHLine(linetype = 3, yintercept = 1)
                    ).show()

            (p + xlim(-10 to 10) + ylim(-10 to 10) +
                    geomVLine(linetype = 3) { xintercept = Pair(-10, 10) } +
                    geomHLine(linetype = 3) { yintercept = listOf(-10, 10) }
                    ).show()

            // scale continuous limits

            (p + scaleXContinuous(limits = -1 to 1) +
                    geomVLine(linetype = 3) { xintercept = -1 to 1 }
                    ).show()

            (p + scaleXContinuous(limits = -1 to 1, expand = listOf(.0, .5)) +
                    geomVLine(linetype = 3) { xintercept = -1 to 1 }
                    ).show()

            (p + scaleXContinuous(limits = -1 to 1, expand = listOf(.0, .5)) +
                    geomVLine(linetype = 3) { xintercept = -1 to 1 } +
                    scaleYContinuous(limits = -2 to 2, expand = listOf(.0, 1)) +
                    geomHLine(linetype = 3) { yintercept = -2 to 2 }
                    ).show()
        }
    }
}