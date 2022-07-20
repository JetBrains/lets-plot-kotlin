/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.scaleColorDiscrete

object AsDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("as_discrete") {
            val dat = mapOf<String, Any>(
                "x" to listOf(0, 5, 10, 15),
                "y" to listOf(0, 5, 10, 15),
                "a" to listOf(1, 2, 3, 4),
                "b" to listOf(4, 5, 6, 7)
            )

            // scale
            run {
                val p = ggplot(dat) { x = "x"; y = "y" } +
                        geomPoint(size = 9.0) { color = "a" } + scaleColorDiscrete(name = "custom name") +
                        ggtitle("scale_color_discrete()")
                p.show()
            }

            // as_discrete, no scale
            run {
                val p = ggplot(dat) { x = "x"; y = "y" } +
                        geomPoint(size = 9.0) { color = asDiscrete(variable = "a", label = "custom name") } +
                        ggtitle("color as_discrete()")
                p.show()
            }

            // as_discrete, scale
            run {
                val p = ggplot(dat) { x = "x"; y = "y" } +
                        geomPoint(shape = 21, size = 9.0, stroke = 5.0) { color = "a"; fill = asDiscrete("b") } +
                        scaleColorDiscrete() +
                        ggtitle("scale_color_discrete(), fill as_discrete()")
                p.show()
            }

            // data in mappings, scale_color_discrete
            run {
                val p = ggplot() +
                        geomPoint(size = 9.0) {
                            x = listOf(0, 5, 10)
                            y = listOf(0, 5, 10)
                            color = listOf(1, 2, 4)
                        } +
                        scaleColorDiscrete() +
                        ggtitle("scale_color_discrete()")
                p.show()
            }

            // as_discrete should work when it is used in "ggplot()" mapping
            run {
                val p = ggplot(dat) { x = "x"; y = "y"; color = asDiscrete(variable = "a") } +
                        geomPoint(size = 9.0) +
                        ggtitle("color as_discrete()")
                p.show()
            }

            // add reordering
            run {
                val p = ggplot(dat) { x = "x"; y = "y"; color = asDiscrete(variable = "a", order=-1) } +
                        geomPoint(size = 9.0) +
                        ggtitle("color as_discrete(order=-1)")
                p.show()
            }
        }
    }
}