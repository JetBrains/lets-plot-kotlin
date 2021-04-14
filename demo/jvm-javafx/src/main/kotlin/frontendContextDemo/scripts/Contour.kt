/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geom_contour
import jetbrains.letsPlot.geom.geom_contourf
import jetbrains.letsPlot.geom.geom_path
import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scale_color_gradient
import jetbrains.letsPlot.stat.stat_contour
import jetbrains.letsPlot.stat.stat_contourf
import kotlin.math.PI
import kotlin.math.sin

object Contour {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Contour") {
            val data = contourData()

            val p = letsPlot(data, fun GenericAesMapping.() {
                x = "x"; y = "y"
            })

            (p + geom_contour(color = "red") { z = "z" }).show()
            (p + geom_contour(bins = 20) { z = "z"; color = "..level.." } +
                    scale_color_gradient(low = "dark_green", high = "yellow")).show()

            // Path + contour stat ==> same
            (p + geom_path(stat = Stat.contour(bins = 20) { z = "z" }) { color = "..level.." } +
                    scale_color_gradient(low = "dark_green", high = "yellow")).show()

            // stat_contour ==> same
            (p + stat_contour(bins = 20) { z = "z"; color = "..level.." } +
                    scale_color_gradient(low = "dark_green", high = "yellow")).show()

            (p + geom_contourf { z = "z"; fill = "..level.." }).show()

            (p + stat_contourf { z = "z"; fill = "..level.." }).show()
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