/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.geom.geom_hline
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.geom.geom_vline
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot

object HLineVLineScripts {
    fun script0() {
        val data = mapOf<String, Any>(
            "x" to (0..4).toList(),
            "y" to listOf(0.0, 2.0, 4.0, 3.0, 1.0),
            "c" to listOf(
                Color.DARK_MAGENTA,
                Color.YELLOW,
                Color.LIGHT_BLUE,
                Color.GREEN,
                Color.WHITE
            )
        )

        val p = lets_plot(data) { x = "x"; y = "y" } +
                ggtitle("Horizontal and vertical lines of different styles + points") +
                geom_point(size = 80.0, alpha = 0.2, show_legend = false) { color = "c" } +
                geom_hline(yintercept = 3.7) +
                geom_hline(yintercept = 2.7, color = "red", linetype = "dashed", size = 1.0) +
                geom_hline(yintercept = 1.7, color = "green", linetype = "dotted", size = 2.0) +
                geom_hline(yintercept = 0.7, color = "orange", linetype = "dotdash", size = 3.0) +
                geom_vline(xintercept = 0.7, color = "black", linetype = "longdash", size = 1.5, alpha = 0.5) +
                geom_vline(xintercept = 1.7, color = "#770077", linetype = "twodash", size = 1.0) +
                geom_vline(xintercept = 2.7, color = "blue", linetype = "blank", size = 0.7) +
                geom_vline(xintercept = 3.7)

        p.show()
    }

    fun script1() {
        val cdata = mapOf(
            "cond" to listOf("A", "B"),
            "rating" to listOf(-0.011843241476365302, 0.5547269440141214)
        )
        val p = ggplot() +
                geom_vline(data = cdata, linetype = "dashed", size = 1.0) {
                    xintercept = "rating"
                    color = "cond"
                }

        p.show()
    }

    fun script2() {
        // This wasn't working in a notebook with Kotlin kernel
        val input = mapOf(
            "A" to -0.011843241476365302,
            "B" to 0.5547269440141214
        )
        val cdata = mapOf(
            "cond" to input.keys,
            "rating" to input.values
        )
        val p = ggplot() +
                geom_vline(data = cdata, linetype = "dashed", size = 1.0) {
                    xintercept = "rating"
                    color = "cond"
                }

        p.show()
    }

}