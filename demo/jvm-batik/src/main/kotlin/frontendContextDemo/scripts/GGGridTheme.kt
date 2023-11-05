/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.geom.geomPie
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.gggrid
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.scaleYContinuous
import org.jetbrains.letsPlot.themes.*

object GGGridTheme {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "gggrid() + theme()",
            maxCol = 3
        ) {
            irisTriple(firstElemTheme = flavorSolarizedLight()).show()
            irisTriple(theme = themeGrey() + flavorDarcula() + theme(plotMargin = margin(40, 40, 40, 40))).show()
            irisTriple(
                theme = themeGrey() + flavorDarcula(),
                firstElemTheme = flavorSolarizedLight() + themeBW()
            ).show()
            irisTriple_compositeCell().show()
            irisTriple_compositeCell(theme = themeBW() + flavorDarcula()).show()
            irisTriple_compositeCell(
                theme = themeBW() + flavorDarcula(),
                innerCompositTheme = flavorSolarizedLight() + themeGrey()
            ).show()
        }
    }

    private fun irisTriple(
        colWidths: List<Double>? = null,
        rowHeights: List<Double>? = null,
        innerAlignment: Boolean = false,
        theme: Feature? = null,
        firstElemTheme: Feature? = null,
    ): Figure {

        val scatter = irisScatterPlot()
        val density = irisDensityPlot()

        val firstPlot = firstElemTheme?.let {
            density + it
        } ?: density

        return gggrid(
            plots = listOf(
                firstPlot, simplePie(),
                scatter, density
            ),
            ncol = 2,
            widths = colWidths,
            heights = rowHeights,
            align = innerAlignment
        ).let {
            if (theme != null) {
                it + theme
            } else {
                it
            }
        }
    }

    @Suppress("FunctionName")
    private fun irisTriple_compositeCell(
        colWidths: List<Double>? = null,
        rowHeights: List<Double>? = null,
        innerAlignment: Boolean = false,
        theme: Feature? = null,
        innerCompositTheme: Feature? = null,
    ): Figure {

        val scatter = irisScatterPlot()
        val density = irisDensityPlot()

        val innerSubPlots = gggrid(
            listOf(scatter, density),
            ncol = 2,
            align = false,
        ).let {
            if (innerCompositTheme != null) {
                it + innerCompositTheme
            } else {
                it
            }
        }

        return gggrid(
            listOf(density, innerSubPlots),
            ncol = 1,
            widths = colWidths,
            heights = rowHeights,
            align = innerAlignment
        ).let {
            if (theme != null) {
                it + theme
            } else {
                it
            }
        }
    }

    private fun irisScatterPlot(): Plot {
        val irisData = Iris.map()
        return ggplot(irisData) +
                geomPoint(size = 5) {
                    x = "sepal length (cm)"
                    y = "sepal width (cm)"
                } +
                ggtitle("Bottom-Left")

    }

    private fun irisDensityPlot(): Plot {
        val irisData = Iris.map()
        return ggplot(irisData) +
                geomDensity {
                    x = "sepal length (cm)"
                } +
                scaleYContinuous(position = "right")
    }

    private fun simplePie(): Plot {
        val data = mapOf(
            "name" to listOf("rock", "paper", "scissors"),
            "slice" to listOf(1, 3, 3)
        )

        return ggplot(data) + geomPie(stat = Stat.identity, sizeUnit = "x", size = 0.5) {
            fill = "name"
            slice = "slice"
        }
    }
}