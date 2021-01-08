/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.bistro.corr.CorrPlot

object CorrPlotDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Corr plot") {
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

            CorrPlot(data, "Upper", showLegend = false)
                .points(type = "upper")
                .build().show()

            CorrPlot(data, "Lower", showLegend = false)
                .points(type = "lower")
                .build().show()

//            CorrPlot(data, "Cars MPG", showLegend = false)
//                .points()
//                .build().show()
//
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