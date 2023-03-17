/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.gggrid
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.*

object ScaleColorDefault {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Color scale (default)", maxCol = 2
        ) {
            continuousDefault()
            discreteDefault()
        }
    }

    private fun continuousDefault() {
        val data = mapOf("x" to (-64..64).toList())

        val p = ggplot(data) + ggsize(600, 200)

        val tiles1 = geomTile(width = 1.0, height = 10.0) {
            x = "x"
            color = "x"
            fill = "x"
        }
        val tiles2 = geomTile(width = 1.0, height = 10.0, colorBy = "paint_a", fillBy = "paint_a") {
            x = "x"
            paint_a = "x"
            paint_a = "x"
        }

        val plist = listOf(
            p + tiles1 + ggtitle("No Scale"),
            p + tiles1 + scaleColorContinuous(name = "Cont") + scaleFillContinuous(name = "Cont") + ggtitle("Continuous, def I: c+f"),
            p + tiles1 + scaleContinuous(listOf("color", "fill"), name = "Cont") + ggtitle("Continuous, def I: [c,f]"),
            p + tiles2 + ggtitle("No Scale II"),
            p + tiles2 + scaleContinuous("paint_a", name = "Cont") + ggtitle("Continuous, def II: paint_a"),
        )

        gggrid(plist, ncol = 1).show()
    }

    private fun discreteDefault() {
        val data = mapOf("x" to ('a'..'g'))

        val p = ggplot(data) + ggsize(600, 200)
        val tiles1 = geomTile(width = 1.0, height = 0.5) {
            x = "x"
            color = "x"
            fill = "x"
        }
        val tiles2 = geomTile(width = 1.0, height = 0.5, colorBy = "paint_a", fillBy = "paint_a") {
            x = "x"
            paint_a = "x"
            paint_a = "x"
        }

        val plist = listOf(
            p + tiles1 + ggtitle("No Scale"),
            p + tiles1 + scaleColorDiscrete(name = "Disc") + scaleFillDiscrete(name = "Disc") + ggtitle("Discrete, def I: c+f"),
            p + tiles1 + scaleDiscrete(listOf("color", "fill"), name = "Disc") + ggtitle("Discrete, def I: [c,f]"),
            p + tiles2 + ggtitle("No Scale II"),
            p + tiles2 + scaleDiscrete("paint_a", name = "Disc") + ggtitle("Discrete, def II: paint_a"),
        )

        gggrid(plist, ncol = 1).show()
    }
}
