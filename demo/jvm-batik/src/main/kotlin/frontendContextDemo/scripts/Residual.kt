/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.bistro.residual.residualPlot
import org.jetbrains.letsPlot.label.ggtitle

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
                    x = "petal length (cm)",
                    y = "petal width (cm)",
                    method = "loess",
                    span = .25,
                    maxN = 25
                ) + ggtitle("Change method")
                p.show()
            }
        }
    }
}