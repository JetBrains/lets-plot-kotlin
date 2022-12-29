/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.bistro.residual.residualPlot
import org.jetbrains.letsPlot.geom.geomArea
import org.jetbrains.letsPlot.ggmarginal
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.themes.themeClassic
import org.jetbrains.letsPlot.themes.themeMinimal

object Residual {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Residual plot") {
            val irisData = Iris.map()

            run {
                val p = residualPlot(irisData, x = "petal length (cm)", y = "petal width (cm)") + ggtitle("Basic demo")
                p.show()
            }

            run {
                val p = residualPlot(
                    irisData,
                    "petal length (cm)",
                    "petal width (cm)",
                    method = "none",
                    hline = false,
                    marginal = "none",
                ) + themeClassic() + ggtitle("Scatter plot (method = 'none')")
                p.show()
            }

            run {
                (residualPlot(
                    irisData,
                    "petal length (cm)",
                    "petal width (cm)",
                    geom = "tile",
                    marginal = "hist:tr"
                ) + ggtitle("geom type = 'tile'")).show()
            }

            run {
                val p = residualPlot(
                    irisData,
                    x = "petal length (cm)",
                    y = "petal width (cm)",
                    method = "loess",
                    span = 0.25,
                    maxN = 25
                ) + ggtitle("method = 'loess'")
                p.show()
            }

            run {
                val p = residualPlot(
                    irisData,
                    "petal length (cm)",
                    "petal width (cm)",
                    marginal = "box:lb:.03,hist:t:.4,hist:r", color = "black"
                ) + ggmarginal(
                    "tr",
                    layer = geomArea(stat = Stat.density(), color = "magenta", fill = "magenta", alpha = .1)
                ) + themeMinimal() + ggtitle("Marginal layers customization")
                p.show()
            }

            // Grouping
            (residualPlot(irisData, "petal length (cm)", "petal width (cm)", colorBy = "target")).show()

            val data = mapOf(
                "x" to listOf(0, 1, 0, 1),
                "y" to listOf(0, 1, 0, -1),
                "g" to listOf("a", "a", "b", "b")
             )
             residualPlot(data, x = "x", y = "y", colorBy = "g").show()
        }
    }
}