/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geomImage
import jetbrains.letsPlot.ggplot

object Image {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Image") {

            fun sampleImageDataUrl3x3(): String {
                return "data:image/png;base64," + "iVBORw0KGgoAAAANSUhEUgAAAAMAAAADCAYAAABWKLW/AAAALUlEQVQYV2MU0nb+L28fxaDiHsnAKKTj3CNvF8WgCuKIaLpLytsGMci7xzAAAKIrB5V5IK5KAAAAAElFTkSuQmCC"
            }

            val data = mapOf(
                "xmin" to listOf(-0.5),
                "ymin" to listOf(-0.5),
                "xmax" to listOf(2.5),
                "ymax" to listOf(1.5)
            )
            val p = ggplot(data) +
                    geomImage(
                        href = sampleImageDataUrl3x3()
                    ) {
                        xmin = "xmin";
                        ymin = "ymin";
                        xmax = "xmax";
                        ymax = "ymax"
                    }
            p.show()
        }
    }
}