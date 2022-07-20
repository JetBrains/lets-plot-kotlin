/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.geom.geomViolin
import org.jetbrains.letsPlot.ggplot

object Violin {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Violin plot") {
            val mpgData = AutoMpg.map()

            val p = ggplot(mpgData) { x = "number of cylinders"; y = "miles per gallon" }

            (p + geomViolin { fill = asDiscrete(variable = "number of cylinders", order = 1) }).show()

            (p + geomViolin(scale = "width", drawQuantiles = listOf(0.25, 0.5, 0.75))).show()

            // todo Add more examples
        }
    }
}