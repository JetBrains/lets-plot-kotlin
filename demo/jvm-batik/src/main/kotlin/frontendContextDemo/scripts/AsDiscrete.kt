/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.as_discrete
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.scale.scale_color_discrete

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
                        geom_point(size = 9.0) { color = "a" } + scale_color_discrete(name = "custom name") +
                        ggtitle("scale_color_discrete()")
                p.show()
            }

            // as_discrete, no scale
            run {
                val p = ggplot(dat) { x = "x"; y = "y" } +
                        geom_point(size = 9.0) { color = as_discrete(variable = "a", label = "custom name") } +
                        ggtitle("color as_discrete()")
                p.show()
            }

            // as_discrete, scale
            run {
                val p = ggplot(dat) { x = "x"; y = "y" } +
                        geom_point(shape = 21, size = 9.0, stroke = 5.0) { color = "a"; fill = as_discrete("b") } +
                        scale_color_discrete() +
                        ggtitle("scale_color_discrete(), fill as_discrete()")
                p.show()
            }

            // data in mappings, scale_color_discrete
            run {
                val p = ggplot() +
                        geom_point(size = 9.0) {
                            x = listOf(0, 5, 10)
                            y = listOf(0, 5, 10)
                            color = listOf(1, 2, 4)
                        } +
                        scale_color_discrete() +
                        ggtitle("scale_color_discrete()")
                p.show()
            }

            // as_discrete should work when it is used in "ggplot()" mapping
            run {
                val p = ggplot(dat) { x = "x"; y = "y"; color = as_discrete(variable = "a") } +
                        geom_point(size = 9.0) +
                        ggtitle("color as_discrete()")
                p.show()
            }
        }
    }
}