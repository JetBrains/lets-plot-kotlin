/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.gggrid
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.scaleYContinuous
import org.jetbrains.letsPlot.themes.themeBW

object PlotGridDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "gggrid()",
            maxCol = 2
        ) {
            irisTriple(innerAlignment = false).show()
            irisTriple(innerAlignment = true).show()
            irisTriple_compositeCell(innerAlignment = false).show()
            irisTriple_compositeCell(innerAlignment = true).show()
            irisTriple(
                colWidths = listOf(1.0, 0.5),
                rowHeights = listOf(0.5, 1.0),
                innerAlignment = false
            ).show()
            irisTriple(
                colWidths = listOf(1.0, 0.5),
                rowHeights = listOf(0.5, 1.0),
                innerAlignment = true
            ).show()
        }
    }

    private fun irisTriple(
        colWidths: List<Double>? = null,
        rowHeights: List<Double>? = null,
        innerAlignment: Boolean
    ): Figure {

        val scatter = irisScatterPlot()
        val density = irisDensityPlot()

        return gggrid(
            plots = listOf(
                density, null,
                scatter, density
            ),
            ncol = 2,
            widths = colWidths,
            heights = rowHeights,
            align = innerAlignment
        )
    }

    @Suppress("FunctionName")
    private fun irisTriple_compositeCell(
        colWidths: List<Double>? = null,
        rowHeights: List<Double>? = null,
        innerAlignment: Boolean
    ): Figure {

        val scatter = irisScatterPlot()
        val density = irisDensityPlot()

        val innerSubPlots = gggrid(
            listOf(scatter, density),
            ncol = 2,
            align = false,
        )

        return gggrid(
            listOf(density, innerSubPlots),
            ncol = 1,
            widths = colWidths,
            heights = rowHeights,
            align = innerAlignment
        )
    }

    private fun irisScatterPlot(): Plot {
        val irisData = Iris.map()
        return ggplot(irisData) +
                geomPoint(size = 5, color = "black", alpha = 0.4) {
                    x = "sepal length (cm)"
                    y = "sepal width (cm)"
                } +
                themeBW() +
                ggtitle("Bottom-Left")

    }

    private fun irisDensityPlot(): Plot {
        val irisData = Iris.map()
        return ggplot(irisData) +
                geomDensity(size = 1.5, color = "black", fill = "black", alpha = 0.1) {
                    x = "sepal length (cm)"
                } +
                scaleYContinuous(position = "right") +
                themeBW()
    }

}