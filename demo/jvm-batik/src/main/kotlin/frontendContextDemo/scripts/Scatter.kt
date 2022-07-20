/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot

object Scatter {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scatter") {
            val data = mapOf<String, Any>(
                "weight" to listOf(68, 84, 73),
                "height" to listOf(175, 169, 180),
                "shoe" to listOf(42, 40, 45)
            )


            val p = ggplot(data) +
                    geomPoint(color = "red") {
                        x = "weight"
                        y = "height"
                        size = "shoe"
                    }

            p.show()
        }
    }
}