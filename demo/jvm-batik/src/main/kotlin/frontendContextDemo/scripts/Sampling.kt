/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.sampling.samplingRandom
import kotlin.math.PI
import kotlin.math.sin

object Sampling {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Sampling") {
            val dat = mapOf<String, Any>(
                "x" to (0..100).map { it * 2 * PI / 100 },
                "y" to (0..100).map { sin(it * 2 * PI / 100) }
            )

            val p = letsPlot(dat) +
                    geomPoint(data = dat, sampling = samplingRandom(40)) { x = "x"; y = "y" }

            p.show()
        }
    }
}