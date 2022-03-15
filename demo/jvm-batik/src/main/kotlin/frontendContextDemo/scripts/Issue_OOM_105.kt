/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.scale.*

@Suppress("ClassName")
object Issue_OOM_105 {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Issue #105 (OOM)") {

            val data = mapOf(
                "Task interval coefficient of variation" to listOf(
                    1.17546,
                    1.19216,
                    1.20941,
                    1.22708,
                    1.24521,
                    1.26378,
                    1.28274,
                    1.30207,
                    1.32178,
                    1.34187,
                    1.3623,
                    1.383,
                    1.40404,
                    1.42535,
                    1.44695,
                    1.46884,
                    1.49097,
                    1.51339,
                    1.53597,
                    1.55886
                ),
                "Average processing time" to listOf(
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                    41.1, //41.1325,
                )
            )

            val plot = ggplot(data) {
                x = "Task interval coefficient of variation"
                y = "Average processing time"
//            } + geomPoint() + geomSmooth() + xlim(41.13249 to 41.13251)
//            } + geomPoint() + geomSmooth() + xlim(listOf(41.13249, 41.13251))
//            } + geomPoint() + geomSmooth() + scaleXContinuous(limits = Pair(41.13249, 41.13251))
//            } + geomPoint() + geomSmooth() + ylim(41.13249 to 41.13251)
//            } + geomPoint()
            } + geomSmooth() + ylim(41.09 to 41.11)


            plot.show()
        }
    }
}