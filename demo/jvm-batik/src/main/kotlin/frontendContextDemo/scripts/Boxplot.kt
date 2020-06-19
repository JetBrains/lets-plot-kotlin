/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_boxplot
import jetbrains.letsPlot.geom.geom_jitter
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.stat.stat_boxplot
import kotlin.math.abs

object Boxplot {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Boxplot") {

            val categories = listOf("A", "B", "C", "D", "E")

            val n = 5000
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "val" to List(n) { rand.nextGaussian() },
//                "cat" to List(n) { categories[abs(rand.nextInt()).rem(categories.size)] }
                // put more points at index 0, then 1 etc
                "cat" to List(n) { categories[abs((rand.nextGaussian() * 1.5).toInt()).rem(categories.size)] }
            )

            val p = lets_plot(data) { x = "cat"; y = "val" }

            (p + geom_jitter()).show()
            (p + geom_boxplot(outlierColor = "red")).show()
            (p + geom_boxplot(outlierColor = "red", varWidth = true)).show()
            (p + stat_boxplot(outlierColor = "red", varWidth = true, fatten = 2.0)).show()
        }
    }
}
