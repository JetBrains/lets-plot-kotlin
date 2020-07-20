/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.*
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

            val p = lets_plot(data) +
                    geom_point { x = "x"; y = "y"; color = "formula"; size = "formula" } +
                    scale_size_manual(values = listOf(7, 3))
            p.show()

            (p + scale_y_log10() + ggtitle("scale_y_log10()")).show()
            (p + scale_y_log10() + scale_x_log10() + ggtitle("scale_y_log10() + scale_x_log10()")).show()

            // the same
            (p + scale_y_continuous(trans = "log10") + scale_x_continuous(trans = "log10") +
                    ggtitle("scale_y_continuous(log10) + scale_x_continuous(log10)")).show()
        }
    }
}