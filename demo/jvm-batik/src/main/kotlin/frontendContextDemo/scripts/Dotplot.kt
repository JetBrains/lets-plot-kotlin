/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.coord.coordFlip
import org.jetbrains.letsPlot.facet.facetGrid
import org.jetbrains.letsPlot.geom.geomDotplot
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.themeGrey

object Dotplot {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Dotplot") {
            val irisData = Iris.map()

            val p = ggplot(irisData) { x = "sepal length (cm)" }

            // Basic
            (p + geomDotplot() + ggtitle("Default")).show()

            // method = "histodot"
            (p + geomDotplot(method = "histodot") + ggtitle("method = \"histodot\"")).show()

            // flip
            (p + geomDotplot(binWidth = 0.2) + coordFlip() + ggtitle("binWidth = 0.2, coord flip")).show()

            // facets
            (p + geomDotplot { fill = "target" } + facetGrid(
                x = "target",
                xOrder = 1,
                yOrder = 1
            ) + themeGrey() +
                    ggtitle("Facets")).show()

            // stack groups
            (p + geomDotplot(method = "histodot", bins = 20, stackGroups = true, color = Color.BLACK) {
                fill = "target"
            } + ggtitle("bins = 20, stackGroups = true")).show()

            // stackDir = "centerwhole"
            (p + geomDotplot(stackDir = "centerwhole", stackRatio = 0.5, dotSize = 2) {
                fill = "target"
            } + ggtitle("stackDir = \"centerwhole\", stackRatio = 0.5, dotSize = 2")).show()


            // stat = "identity"

            val data = mapOf(
                "x" to listOf(0.5, 1.5, 2.5, null),
                "count" to listOf(0, 2, null, 0),
                "binwidth" to listOf(.5, .5, .5, .5),
            )

            (letsPlot(data) + geomDotplot(stat = Stat.identity) {
                x = "x"
                stackSize = "count"
                binWidth = "binwidth"
            } + ggtitle("stat = Stat.identity")).show()
        }
    }
}