/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleXContinuous
import org.jetbrains.letsPlot.scale.scaleXLog10

object ScaleTransform {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Scale transformations") {
            val data = mapOf<String, Any>(
                "x" to (0..10).toList(),
                "y" to (0..10).toList()
            )

            val p = letsPlot(data) + geomLine { x = "x"; y = "y" }

            p.show()
            (p + scaleXContinuous(trans = "reverse") + ggtitle("reverse")).show()
            (p + scaleXContinuous(trans = "log10") + ggtitle("log10")).show()
            (p + scaleXLog10() + ggtitle("scale_x_log10")).show()
            (p + scaleXContinuous(trans = "sqrt") + ggtitle("sqrt")).show()
        }
    }
}
