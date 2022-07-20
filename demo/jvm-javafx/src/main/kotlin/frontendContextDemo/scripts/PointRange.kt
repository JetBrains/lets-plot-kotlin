/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.geom.geomPointRange
import org.jetbrains.letsPlot.ggplot

object PointRange {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("PointRange") {
            val data = mapOf<String, Any>(
                "drink" to listOf("coffee", "tea", "water", "milk"),
                "mean" to listOf(3, 4, 6, 2),
                "upper" to listOf(1, 5, 7, 4),
                "lower" to listOf(6, 3, 2, 1)
            )

            val geom = geomPointRange(fatten = 10.0) {
                x = "drink"; y = "mean"; ymin = "lower"; ymax = "upper"; fill = "mean"
            }

            val p = ggplot(data) + geom
            p.show()
        }
    }
}