/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.facet.facet_wrap
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.letsPlot
import java.awt.Dimension

object FacetWrap {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Facet wrap",
            2,
            Dimension(600, 400)
        ) {
            val data = AutoMpg.map()

            val p = letsPlot(data) + geom_point {
                x = "engine horsepower"
                y = "miles per gallon"
                color = "origin of car"
            }

            // one facet
            (p + facet_wrap(
                facets = "number of cylinders",
                format = "{d} cyl"
            )).show()

            // one facet, 3 cols
            (p + facet_wrap(
                facets = "number of cylinders",
                ncol = 3,
                format = "{d} cyl"
            )).show()

            // one facet, 4 rows
            (p + facet_wrap(
                facets = "number of cylinders",
                nrow = 4,
                format = "{d} cyl",
                dir = "v"
            )).show()

            // two facets
            (p + facet_wrap(
                facets = listOf(
                    "origin of car",
                    "number of cylinders"
                ),
                ncol = 5,
                format = listOf(null, "{d} cyl")
            )).show()

            // two facets, cylinders order: desc.
            (p + facet_wrap(
                facets = listOf(
                    "origin of car",
                    "number of cylinders"
                ),
                ncol = 5,
                order = listOf(null, -1),
                format = listOf(null, "{d} cyl")
            )).show()
        }
    }
}