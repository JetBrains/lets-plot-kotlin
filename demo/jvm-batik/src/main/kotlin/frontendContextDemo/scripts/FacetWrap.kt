/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.facet.facetWrap
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themeGrey
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

            val p = letsPlot(data) + geomPoint {
                x = "engine horsepower"
                y = "miles per gallon"
                color = "origin of car"
            } + themeGrey()

            // one facet
            (p + facetWrap(
                facets = "number of cylinders",
                format = "{d} cyl"
            )).show()

            // one facet, 3 cols
            (p + facetWrap(
                facets = "number of cylinders",
                ncol = 3,
                format = "{d} cyl"
            )).show()

            // one facet, 4 rows
            (p + facetWrap(
                facets = "number of cylinders",
                nrow = 4,
                format = "{d} cyl",
                dir = "v"
            )).show()

            // two facets
            (p + facetWrap(
                facets = listOf(
                    "origin of car",
                    "number of cylinders"
                ),
                ncol = 5,
                format = listOf(null, "{d} cyl")
            )).show()

            // two facets, cylinders order: desc.
            (p + facetWrap(
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