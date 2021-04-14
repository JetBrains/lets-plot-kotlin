/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scale_x_continuous
import jetbrains.letsPlot.scale.scale_x_log10

object ScaleTransform {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale transformations") {
            val data = mapOf<String, Any>(
                "x" to (0..10).toList(),
                "y" to (0..10).toList()
            )

            val p = letsPlot(data) + geom_line { x = "x"; y = "y" }

            p.show()
            (p + scale_x_continuous(trans = "reverse") + ggtitle("reverse")).show()
            (p + scale_x_continuous(trans = "log10") + ggtitle("log10")).show()
            (p + scale_x_log10() + ggtitle("scale_x_log10")).show()
            (p + scale_x_continuous(trans = "sqrt") + ggtitle("sqrt")).show()
        }
    }
}
