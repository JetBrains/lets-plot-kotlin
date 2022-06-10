/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomQQ
import jetbrains.letsPlot.geom.geomQQ2
import jetbrains.letsPlot.geom.geomQQ2Line
import jetbrains.letsPlot.geom.geomQQLine
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle

object QQ {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Q-Q plot") {
            val irisData = Iris.map()

            run {
                val p = ggplot(irisData) { sample = "sepal length (cm)" }
                (p + geomQQ(distribution = "norm") + geomQQLine(distribution = "norm") + ggtitle("geomQQ + geomQQLine")).show()
            }

            run {
                val p = ggplot(irisData) { x = "sepal width (cm)"; y = "sepal length (cm)" }
                (p + geomQQ2() + geomQQ2Line() + ggtitle("geomQQ2 + geomQQ2Line")).show()
            }
        }
    }
}