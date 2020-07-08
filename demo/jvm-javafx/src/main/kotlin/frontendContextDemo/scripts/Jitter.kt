/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geom_jitter
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.scale.scale_x_discrete

object Jitter {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Jitter") {
            val rand = java.util.Random()
            val data = mapOf<String, Any>(
                "d" to List(100) { rand.nextGaussian() } + List(100) { rand.nextGaussian() + .0 } + List(100) { rand.nextGaussian() + 1.0 },
                "l" to List(100) { "A" } + List(100) { "B" } + List(100) { "C" }
            )

            val p = ggplot(data) { x = "l"; y = "d" }

            (p + geom_point(shape = 21, color = "black", fill = "white", size = 3, stroke = 3)).show()

            (p + geom_jitter(shape = 21, fill = "white", size = 3, stroke = 3)).show()

            val geom = geom_jitter(size = 3.0, width = .2) { color = "l" }
            (p + geom).show()

            (p + geom + scale_x_discrete(expand = listOf(0, .1))).show()
        }
    }
}