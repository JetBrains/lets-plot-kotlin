/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.*
import org.jetbrains.letsPlot.coord.coordFixed
import org.jetbrains.letsPlot.geom.geomFunction
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.scale.scaleColorGradient
import org.jetbrains.letsPlot.scale.scaleSize
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

object GeomFunction {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("geomFunction()") {

            val ran = Random(37)
            val norm: (Any) -> Double = { ran.nextGaussian() }
            (letsPlot() + geomFunction(func = norm, xlim = -3.0 to 3.0)).show()

            ////

            val data = mapOf("x" to (-5..5).map { it })
            (letsPlot() +
                    geomFunction(data = data, func = { t: Double -> t.pow(2.0) }, showLegend = false) {
                        x = "x"; color = "y"; size = "y"
                    } +
                    scaleColorGradient(low = "red", high = "green") +
                    scaleSize(range = 1 to 4, trans = "reverse")
                    ).show()

            ////

            fun funLayer(func: (Double) -> Double) = geomFunction(
                func = func,
                xlim = -2 to 2,
                n = 9,
                stat = Stat.density2D(),
                geom = GeomOptions(GeomKind.DENSITY2D)
            )
            gggrid(
                listOf(
                    letsPlot() + funLayer { t -> t },
                    letsPlot() + funLayer { t -> t.pow(2.0) },
                    letsPlot() + funLayer { t -> 2.0.pow(t) },
                    letsPlot() + funLayer { t -> sqrt(4 - t.pow(2.0)) } + coordFixed(ratio = 2),
                ),
                ncol = 2
            ).show()
        }
    }
}