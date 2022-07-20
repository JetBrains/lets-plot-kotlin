/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.facet.facetGrid
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themeGrey
import java.awt.Dimension

object FacetGridFreeScales {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Facet grid, free scales",
            2,
            Dimension(600, 400)
        ) {
            val data = AutoMpg.map()

            val p = letsPlot(data) + geomPoint {
                x = "engine horsepower"
                y = "miles per gallon"
                color = "origin of car"
            } + themeGrey()

            // cols
            (p + facetGrid(
                x = "number of cylinders",
                scales = "free_x",
                xFormat = "{d} cyl"
            ) + ggtitle(
                "scales='free_x'"
            )).show()

            // rows
            (p + facetGrid(
                y = "origin of car",
                scales = "free_y"
            ) + ggtitle(
                "scales='free_y'"
            )).show()

            // both
            (p + facetGrid(
                x = "number of cylinders",
                y = "origin of car",
                scales = "free",
                xFormat = "{d} cyl"
            ) + ggtitle(
                "scales='free'"
            )).show()

            // both flipped
            (p + facetGrid(
                x = "origin of car",
                y = "number of cylinders",
                scales = "free",
                yFormat = "{d} cyl"
            ) + ggtitle(
                "scales='free' (flipped)"
            )).show()
        }
    }
}