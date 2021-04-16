/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.geom.geomABLine
import jetbrains.letsPlot.geom.geomHLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomVLine
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot

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

        val p = letsPlot(data) { x = "x"; y = "y" } +
                ggtitle("Horizontal and vertical lines of different styles + points") +
                geomPoint(size = 80.0, alpha = 0.2, showLegend = false) { color = "c" } +
                geomHLine(yintercept = 3.7) +
                geomHLine(yintercept = 2.7, color = "red", linetype = "dashed", size = 1.0) +
                geomHLine(yintercept = 1.7, color = "green", linetype = "dotted", size = 2.0) +
                geomHLine(yintercept = 0.7, color = "orange", linetype = "dotdash", size = 3.0) +
                geomVLine(xintercept = 0.7, color = "black", linetype = "longdash", size = 1.5, alpha = 0.5) +
                geomVLine(xintercept = 1.7, color = "#770077", linetype = "twodash", size = 1.0) +
                geomVLine(xintercept = 2.7, color = "blue", linetype = "blank", size = 0.7) +
                geomVLine(xintercept = 3.7)

        // ToDo: scale_xxx_identity
        // + scale_color_identity()

        p.show()
    }

    fun script1() {
        val cdata = mapOf(
            "cond" to listOf("A", "B"),
            "rating" to listOf(-0.011843241476365302, 0.5547269440141214)
        )
        val p = ggplot() +
                geomVLine(data = cdata, linetype = "dashed", size = 1.0) {
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
                geomVLine(data = cdata, linetype = "dashed", size = 1.0) {
                    xintercept = "rating"
                    color = "cond"
                }

        p.show()
    }

    fun script3() {
        // use 'alpha' parameter
        val p = ggplot() +
                ggtitle("Use 'alpha' parameter") +
                geomVLine(xintercept = 0, color = "red", size = 2, alpha = 0.1) +
                geomHLine(yintercept = 0, color = "red", size = 2, alpha = 0.5) +
                geomABLine(intercept = 0, slope = 1.0, color = "red", size = 2, alpha = 1) +
                geomABLine(intercept = 0, slope = -1.0, color = "red", size = 2, alpha = 0)
        p.show()
    }
}