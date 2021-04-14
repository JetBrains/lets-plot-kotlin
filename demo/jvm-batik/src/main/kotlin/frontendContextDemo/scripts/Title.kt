/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scale_size_area
import kotlin.math.PI
import kotlin.math.sin

object Title {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("ggtitle(\"Sine function\")") {
            val dat = mapOf<String, Any>(
                "x" to (0..100).map { it * 2 * PI / 100 },
                "y" to (0..100).map { sin(it * 2 * PI / 100) }
            )

            val p = letsPlot(dat) +
                    geom_point { x = "x"; y = "y" } +
                    ggtitle("Sine function") +
                    scale_size_area(maxSize = 70, guide = "none")

            p.show()
        }
    }
}