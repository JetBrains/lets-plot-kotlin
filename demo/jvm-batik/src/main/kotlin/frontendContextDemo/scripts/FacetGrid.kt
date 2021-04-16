/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.facet.facetGrid
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import java.awt.Dimension

object FacetGrid {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Facet grid",
            2,
            Dimension(600, 400)
        ) {
            val data = AutoMpg.map()

            val p = letsPlot(data) + geomPoint {
                x = "engine horsepower"
                y = "miles per gallon"
                color = "origin of car"
            }

            // cols
            (p + facetGrid(
                x = "number of cylinders",
                xFormat = "{d} cyl"
            )).show()

            // rows
            (p + facetGrid(
                y = "origin of car"
            )).show()

            // both
            (p + facetGrid(
                x = "number of cylinders",
                y = "origin of car",
                xFormat = "{d} cyl"
            )).show()

            // both flipped
            (p + facetGrid(
                x = "origin of car",
                y = "number of cylinders",
                yFormat = "{d} cyl"
            )).show()

            // both, Y-order - desc.
            (p + facetGrid(
                x = "number of cylinders",
                y = "origin of car",
                yOrder = -1,
                xFormat = "{d} cyl"
            )).show()
        }
    }
}