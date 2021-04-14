/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geomBoxplot
import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.letsPlot
import kotlin.math.abs

object Boxplot {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Boxplot") {

            val categories = listOf("A", "B", "C", "D", "E")

            val n = 500
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "val" to List(n) { rand.nextGaussian() },
                "cat" to List(n) { categories[abs(rand.nextInt()).rem(categories.size)] }
            )

            val p = letsPlot(data, fun GenericAesMapping.() {
                x = "cat"; y = "val"
            }) + geomBoxplot(outlierColor = "red")
            p.show()
        }
    }
}
