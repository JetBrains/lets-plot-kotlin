/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.datalore.base.random.RandomGaussian
import jetbrains.letsPlot.geom.geom_bin2d
import jetbrains.letsPlot.ggplot
import kotlin.random.Random

object Bin2d {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Bin2d") {
            basic()
            setBinCount()
            setBinWidth()
            setBinWidth_Weight()
            setBinWidth_Weight_Density()
        }
    }

    private val DATA = data()

    private fun data(): Map<String, List<*>> {
        val count = 200

        fun gauss(count: Int, seed: Long, mean: Double, stdDeviance: Double): List<Double> {
            val r = RandomGaussian(Random(seed))
            val list = ArrayList<Double>()
            for (i in 0 until count) {
                val next = r.nextGaussian() * stdDeviance + mean
                list.add(next)
            }
            return list
        }

        val xs = gauss(count, 12, 0.0, 5.0)
        val ys = gauss(count, 21, 0.0, 1.0)
        val weights = ArrayList<Double>()
        for (x in xs) {
            weights.add(if (x < 0.0) 2.0 else 0.5);
        }
        return mapOf(
            "x" to xs,
            "y" to ys,
            "weight" to weights
        )
    }

    private fun basic() {
        val p = ggplot(DATA) { x = "x"; y = "y" } + geom_bin2d()
        p.show()
    }

    private fun setBinCount() {
        val p = ggplot(DATA) { x = "x"; y = "y" } + geom_bin2d(
            bins = 5 to 5
        )
        p.show()
    }

    private fun setBinWidth() {
        val p = ggplot(DATA) { x = "x"; y = "y" } + geom_bin2d(
            binWidth = Pair(3, 3)
        )
        p.show()
    }

    @Suppress("FunctionName")
    private fun setBinWidth_Weight() {
        val p = ggplot(DATA) { x = "x"; y = "y" } + geom_bin2d(
            binWidth = Pair(3, 3)
        ) { weight = "weight" }

        p.show()
    }
    @Suppress("FunctionName")
    private fun setBinWidth_Weight_Density() {
        val p = ggplot(DATA) { x = "x"; y = "y" } + geom_bin2d(
            binWidth = Pair(3, 3)
        ) { weight = "weight"; fill = "..density.." }
        p.show()
    }
}