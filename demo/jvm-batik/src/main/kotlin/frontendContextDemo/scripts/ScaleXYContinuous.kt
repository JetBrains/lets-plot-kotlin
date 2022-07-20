/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.*
import kotlin.math.pow

object ScaleXYContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Continuous position scales") {
            val n = 20
            val xs = (0..n).toList()

            val data = mapOf<String, Any>(
                "x" to xs + xs,
                "y" to xs.map { 10.0.pow(it / 10.0) } + xs.map { it * 5 },
                "formula" to List(n + 1) { "10^(x/10)" } + List(n + 1) { "5*x" }
            )

            val p = letsPlot(data) +
                    geomPoint { x = "x"; y = "y"; color = "formula"; size = "formula" } +
                    scaleSizeManual(values = listOf(7, 3))
            p.show()

            (p + scaleYLog10() + ggtitle("scale_y_log10()")).show()
            (p + scaleYLog10() + scaleXLog10() + ggtitle("scale_y_log10() + scale_x_log10()")).show()

            // the same
            (p + scaleYContinuous(trans = "log10") + scaleXContinuous(trans = "log10") +
                    ggtitle("scale_y_continuous(log10) + scale_x_continuous(log10)")).show()

            // reversed
            (p + scaleXReverse() + ggtitle("scale_x_reverse")).show()
            (p + scaleYReverse() + ggtitle("scale_y_reverse")).show()

            // sqrt
            (p + scaleXSqrt() + ggtitle("scale_x_sqrt")).show()
            (p + scaleYSqrt() + ggtitle("scale_y_sqrt")).show()

        }
    }
}