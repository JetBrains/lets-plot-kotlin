/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.geom.geomJitter
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.scale.scaleXDiscrete

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

            (p + geomPoint(shape = 21, color = "black", fill = "white", size = 3, stroke = 3)).show()

            (p + geomJitter(shape = 21, fill = "white", size = 3, stroke = 3)).show()

            val geom = geomJitter(size = 3.0, width = .2) { color = "l" }
            (p + geom).show()

            (p + geom + scaleXDiscrete(expand = listOf(0, .1))).show()
        }
    }
}