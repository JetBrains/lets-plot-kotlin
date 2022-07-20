/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.bistro.corr.CorrPlot
import java.awt.Dimension

object CorrPlotDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval(
            "Corr plot",
            maxCol = 2,
            plotSize = Dimension(700, 500)
        ) {
            val data = AutoMpg.map()

            CorrPlot(data)
                .points()
                .build().show()

            CorrPlot(data, showLegend = false, flip = false)
                .points()
                .build().show()

            CorrPlot(data, "No diag", showLegend = false)
                .points(diag = false)
                .build().show()

            CorrPlot(data, "Upper", showLegend = true)
                .points(type = "upper")
                .build().show()

            CorrPlot(data, "Upper and diag", showLegend = false)
                .points(type = "upper", diag = true)
                .build().show()

            CorrPlot(data, "Lower", showLegend = false)
                .points(type = "lower")
                .build().show()
            CorrPlot(data, "Lower and diag", showLegend = false)
                .points(type = "lower", diag = true)
                .build().show()

            CorrPlot(data, "threshold = 0.5, no diag", threshold = 0.5)
                .points(diag = false)
                .build().show()

            CorrPlot(data, "threshold = 0.6, with diag", threshold = 0.6)
                .points(diag = true)
                .build().show()

            CorrPlot(data, "threshold = 0.6, no diag", threshold = 0.6)
                .points(diag = false)
                .build().show()

            CorrPlot(data, "threshold = 0.6, upper", threshold = 0.6)
                .points(type = "upper")
                .build().show()

            CorrPlot(data, "threshold = 0.6, upper, diag", threshold = 0.6)
                .points(type = "upper", diag = true)
                .build().show()

            CorrPlot(data)
                .points()
                .tiles()
                .build().show()

            CorrPlot(data)
                .tiles()
                .build().show()

            CorrPlot(data)
                .points()
                .tiles()
                .labels()
                .build().show()

            CorrPlot(data, "palette: BrBG")
                .points()
                .tiles()
                .labels()
                .paletteBrBG()
                .build().show()

            CorrPlot(data, "palette: Spectral")
                .points()
                .tiles()
                .labels()
                .paletteSpectral()
                .build().show()

            CorrPlot(mapOf(
                "a" to listOf(1.0, 0.32, -0.43, 0.55),
                "b" to listOf(0.32, 1.0, 0.78, 0.0),
                "c" to listOf(-0.43, 0.78, 1.0, -0.42),
                "d" to listOf(0.55, 0.0, -0.42, 1.0)
            ), "Computed coefficients")
                .points()
                .labels()
                .build().show()

//            val dataShortened = data.mapKeys { it.key.subSequence(0, 4) }
//            CorrPlot(dataShortened)
//                .points()
//                .build().show()
//
//            CorrPlot(dataShortened, showLegend = false)
//                .points()
//                .build().show()
//
//            CorrPlot(dataShortened, "Cars MPG", showLegend = false)
//                .points()
//                .build().show()

        }
    }
}