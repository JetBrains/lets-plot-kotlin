/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.facet.facetWrap
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.themeGrey
import java.awt.Dimension

object FacetWrapFreeScales {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Facet wrap, free scales",
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

            // free_x
            (p + facetWrap(
                facets = "number of cylinders",
                scales = "free_x",
                format = "{d} cyl"
            ) + ggtitle(
                "scales='free_x'"
            )).show()

            // free_y
            (p + facetWrap(
                facets = "number of cylinders",
                scales = "free_y",
                format = "{d} cyl"
            ) + ggtitle(
                "scales='free_y'"
            )).show()

            // free both
            (p + facetWrap(
                facets = "number of cylinders",
                scales = "free",
                format = "{d} cyl"
            ) + ggtitle(
                "scales='free'"
            )).show()

            // two facets

            // free_x
            (p + facetWrap(
                facets = listOf(
                    "origin of car",
                    "number of cylinders"
                ),
                scales = "free_x",
                ncol = 5,
                format = listOf(null, "{d} cyl")
            ) + ggtitle(
                "scales='free_x'"
            )).show()

            // free_y
            (p + facetWrap(
                facets = listOf(
                    "origin of car",
                    "number of cylinders"
                ),
                scales = "free_y",
                ncol = 5,
                format = listOf(null, "{d} cyl")
            ) + ggtitle(
                "scales='free_y'"
            )).show()

            // free both
            (p + facetWrap(
                facets = listOf(
                    "origin of car",
                    "number of cylinders"
                ),
                scales = "free",
                ncol = 5,
                format = listOf(null, "{d} cyl")
            ) + ggtitle(
                "scales='free'"
            )).show()
        }
    }
}