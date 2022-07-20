/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot

object PointsOverBars {
    @JvmStatic
    @Suppress("DuplicatedCode")
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Points on Bars") {
            val data = mapOf<String, Any>(
                "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
                "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
            )
            val p = ggplot(data)

            // bars
            val bars = geomBar {
                x = "cat1"
                fill = "cat2"
            }

            // points with count stat and `stack` position adjustment
            val points = geomPoint(stat = Stat.count(), position = Pos.stack, size = 15.0) {
                x = "cat1"
                color = "cat2"
            }

            (p + bars + points).show()
        }
    }
}