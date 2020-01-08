/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotDemo.scripts

import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.stat.stat_count
import plotDemo.BrowserDemoFrontend

object BarGeomAndCountStat {
    @JvmStatic
    @Suppress("DuplicatedCode")
    fun main(args: Array<String>) {
        BrowserDemoFrontend.eval("'geom_bar()' == 'stat_count'") {
            val data = mapOf<String, Any>(
                "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
                "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
            )
            val p = ggplot(data)

            // bar (with count stat by default)
            val barLayer = geom_bar {
                x = "cat1"
                fill = "cat2"
            }

            // count stat (with bar geom by default)
            val countLayer = stat_count {
                x = "cat1"
                fill = "cat2"
            }

            // show two identical plots
            (p + barLayer).show()
            (p + countLayer).show()
        }
    }
}