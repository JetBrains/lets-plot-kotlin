/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.sampling.sampling_random
import frontendContextDemo.ScriptInBatikContext
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

            val p = lets_plot(dat) +
                    geom_point(data = dat, sampling = sampling_random(40)) { x = "x"; y = "y" }

            p.show()
        }
    }
}