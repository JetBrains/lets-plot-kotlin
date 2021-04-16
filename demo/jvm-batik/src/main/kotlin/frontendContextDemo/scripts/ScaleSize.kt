/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleSize
import jetbrains.letsPlot.scale.scaleSizeArea

object ScaleSize {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Size Scale") {
            val dat = mapOf<String, Any>(
                "x" to (0..5).map { it },
                "y" to (0..5).map { 0 },
                "s" to (0..5).map { it }
            )

            val p = letsPlot(dat) + geomPoint { x = "x"; y = "y"; size = "s" }

            // size ~= radius
            (p + scaleSize(range = 5 to 50, guide = "none") + ggtitle(
                "scale_size"
            )).show()

            // with reversed transformation
            (p + scaleSize(range = 5 to 50, guide = "none", trans = "reverse") + ggtitle("reversed ")).show()

            // size ~= radius where 0 size --> 0 radius
            (p + scaleSizeArea(maxSize = 50, guide = "none") + ggtitle("scale_size_area")).show()
        }
    }
}