/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotApiDemo.scripts

import plotApiDemo.ScriptInSwingContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.sampling.samplingRandom
import org.jetbrains.letsPlot.sampling.samplingSystematic
import kotlin.math.PI
import kotlin.math.sin

object SamplingCombined {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInSwingContext.eval("SamplingCombined") {
            val n = 1000
            val dat = mapOf<String, Any>(
                "x" to (0 until n).map { it * 2 * PI / n },
                "y" to (0 until n).map { sin(it * 2 * PI / n) }
            )

            // Samplings are chained with `+` and applied left to right:
            // random sampling (500 points), then systematic sampling (100 points) of the result.
            val p = letsPlot(dat) +
                    geomPoint(
                        data = dat,
                        sampling = samplingRandom(500, seed = 42) + samplingSystematic(20)
                    ) { x = "x"; y = "y" }

            p.show()
        }
    }
}
