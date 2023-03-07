/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.bistro.joint.jointPlot
import org.jetbrains.letsPlot.geom.geomArea
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggmarginal
import org.jetbrains.letsPlot.scale.scaleFillGradient

object JointPlot {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Joint plot") {
            val irisData = Iris.map()

            run {
                val p = jointPlot(irisData, "petal length (cm)", "petal width (cm)")
                p.show()
            }

            run {
                val p =
                    jointPlot(irisData, "petal length (cm)", "petal width (cm)", colorBy = "target", geom = "density2d")
                p.show()
            }

            run {
                val p = jointPlot(
                    irisData,
                    "petal length (cm)",
                    "petal width (cm)",
                    color = "black",
                    marginal = "box:lb:.03,hist:t:.4,hist:r"
                ) +
                        ggmarginal(
                            "tr",
                            layer = geomArea(stat = Stat.density(), color = "magenta", fill = "magenta", alpha = 0.1)
                        )
                p.show()
            }

            run {
                val p = jointPlot(
                    irisData,
                    "petal length (cm)",
                    "petal width (cm)",
                    colorBy = "target",
                    marginal = "hist:tr"
                )
                p.show()
            }

            run {
                val p = jointPlot(
                    irisData, "petal length (cm)", "petal width (cm)", geom = "density2df",
                    color = "#993404", alpha = 0.3, regLine = false
                ) +
                        geomPoint(size = 5, shape = 21, color = "#993404", fill = "#ffffd4") +
                        scaleFillGradient(low = "#d95f0e", high = "#fff7bc")
                p.show()
            }
        }
    }
}
