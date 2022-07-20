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
        }
    }

    private fun contourData(): Map<String, List<*>> {
        val countX = 50
        val countY = 50

        fun z_fun(x: Double, y: Double): Double {
            var z = sin(x * 3 * PI / countX)
            z += sin(y * 3 * PI / countY)
            z += x * 3 / countX
            z += y * 5 / countY
            return z
        }

        val x = ArrayList<Double>()
        val y = ArrayList<Double>()
        val z = ArrayList<Double>()
        for (row in 0 until countY) {
            for (col in 0 until countX) {
                x.add(col.toDouble())
                y.add(row.toDouble())
                z.add(z_fun(col.toDouble(), row.toDouble()))
            }
        }
        val map = HashMap<String, List<*>>()
        map["x"] = x
        map["y"] = y
        map["z"] = z
        return map
    }
}