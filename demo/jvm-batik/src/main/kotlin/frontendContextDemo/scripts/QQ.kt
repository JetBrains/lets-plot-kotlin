/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.bistro.qq.qqPlot
import org.jetbrains.letsPlot.geom.geomQQ
import org.jetbrains.letsPlot.geom.geomQQ2
import org.jetbrains.letsPlot.geom.geomQQ2Line
import org.jetbrains.letsPlot.geom.geomQQLine
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.label.xlab
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleColorBrewer
import org.jetbrains.letsPlot.scale.scaleFillBrewer

object QQ {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Q-Q plot") {
            val irisData = Iris.map()
            val gData = mapOf(
                "y" to listOf(-3, -1, 0, 1, 3, null, -4, -2, 0, 1, 2, 4),
                "g" to listOf('A', 'A', 'A', 'A', 'A', 'A', null, 'B', 'B', 'B', 'B', 'B')
            )

            run {
                val p = letsPlot(irisData) { sample = "sepal length (cm)" } +
                        geomQQ(distribution = "norm") +
                        geomQQLine(distribution = "norm") +
                        ggtitle("Basic demo")
                p.show()
            }
            run {
                val p = letsPlot(irisData) { x = "sepal width (cm)"; y = "sepal length (cm)" } +
                        geomQQ2() +
                        geomQQ2Line() +
                        ggtitle("Only sample values")
                p.show()
            }
            run {
                val p = letsPlot(gData) { sample = "y"; color = "g" } +
                        geomQQ() +
                        geomQQLine(quantiles = 0.1 to 0.9) +
                        ggtitle("Grouping")
                p.show()
            }

            // qqPlot
            run {
                val p = qqPlot(irisData, sample = "sepal length (cm)") + ggtitle("Bistro basic demo")
                p.show()
            }
            run {
                val p = qqPlot(
                    irisData,
                    x = "sepal width (cm)",
                    y = "sepal length (cm)"
                ) + ggtitle("Bistro only sample values")
                p.show()
            }
            run {
                val p = qqPlot(gData, sample = "y", group = "g", alpha = 0.8, color = "black", shape = 21) +
                        ggtitle("Bistro grouping") +
                        xlab("Normal distribution quantiles") +
                        scaleColorBrewer(type = "qual", palette = "Set1") +
                        scaleFillBrewer(type = "qual", palette = "Set1")
                p.show()
            }
        }
    }
}