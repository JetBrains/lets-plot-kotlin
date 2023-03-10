/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.geomContour
import org.jetbrains.letsPlot.geom.geomContourFilled
import org.jetbrains.letsPlot.geom.geomPath
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleColorGradient
import org.jetbrains.letsPlot.scale.scaleGradient
import org.jetbrains.letsPlot.stat.statContour
import org.jetbrains.letsPlot.stat.statContourFilled
import kotlin.math.PI
import kotlin.math.sin

object Contour {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Contour") {
            val data = contourData()

            val p = letsPlot(data) { x = "x"; y = "y" }

            (p + geomContour(color = "red") { z = "z" }).show()
            (p + geomContour(bins = 20) { z = "z"; color = "..level.." } +
                    scaleColorGradient(low = "dark_green", high = "yellow")).show()

            // Path + contour stat ==> same
            (p + geomPath(stat = Stat.contour(bins = 20) { z = "z" }) { color = "..level.." } +
                    scaleColorGradient(low = "dark_green", high = "yellow")).show()

            // stat_contour ==> same
            (p + statContour(bins = 20) { z = "z"; color = "..level.." } +
                    scaleColorGradient(low = "dark_green", high = "yellow")).show()

            (p + geomContourFilled { z = "z"; fill = "..level.." }).show()

            (p + statContourFilled { z = "z"; fill = "..level.." }).show()

            // multiple color scales
            val heightData = generateData(3, 3, 3, 5, 11)
            val temperatureData = generateData(1, 2, 5, 4, -0.5)
            (letsPlot() +
                    geomContour(data = heightData, colorBy = "paint_a", bins = 15, size = 1) {
                        x = "x"; y = "y"; z = "z"; paint_a = "..level.."
                    } +
                    geomContour(data = temperatureData,  colorBy = "paint_b", bins = 8, size = 1) {
                        x = "x"; y = "y"; z = "z"; paint_b = "..level.."
                    } +
                    scaleGradient("paint_a", name = "height", low = "#993404", high = "#ffffd4") +
                    scaleGradient("paint_b", name = "temperature", low = "#0571b0", high = "#ca0020")
                    ).show()
        }
    }

    private fun contourData(): Map<String, List<*>> = generateData(3, 3, 3, 5, 1, xMax = 50, yMax = 50)

    private fun generateData(
        p1: Number,
        p2: Number,
        p3: Number,
        p4: Number,
        p5: Number,
        xMax: Int = 400,
        yMax: Int = 300
    ): Map<String, List<*>> {
        fun z_fun(x: Double, y: Double): Double {
            var z = sin(x * p1.toDouble() * PI / xMax)
            z += sin(y * p2.toDouble() * PI / yMax)
            z += x * p3.toDouble() / xMax
            z += y * p4.toDouble() / yMax
            return z * p5.toDouble()
        }

        val x = ArrayList<Double>()
        val y = ArrayList<Double>()
        val z = ArrayList<Double>()
        for (row in 0 until yMax) {
            for (col in 0 until xMax) {
                x.add(col.toDouble())
                y.add(row.toDouble())
                z.add(z_fun(col.toDouble(), row.toDouble()))
            }
        }
        return mapOf("x" to x, "y" to y, "z" to z)
    }
}