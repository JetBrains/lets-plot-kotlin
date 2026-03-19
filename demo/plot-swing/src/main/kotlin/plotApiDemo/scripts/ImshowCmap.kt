/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotApiDemo.scripts

import plotApiDemo.ScriptInSwingContext
import org.jetbrains.letsPlot.geom.RasterData
import org.jetbrains.letsPlot.geom.geomImshow
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.guideColorbar
import org.jetbrains.letsPlot.scale.scaleColorViridis

object ImshowCmap {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInSwingContext.eval("Imshow with cmap", maxCol = 2) {

            // 2 rows x 3 columns greyscale image
            val greyscaleData = RasterData.create(
                listOf(
                    listOf(0, 128, 255),
                    listOf(64, 192, 32)
                )
            )

            // Default greyscale (no cmap)
            (letsPlot() + geomImshow(greyscaleData) + ggtitle("Default greyscale")).show()

            // With a simple 3-color cmap
            (letsPlot() + geomImshow(
                greyscaleData,
                cmap = listOf("#ff0000", "#00ff00", "#0000ff")
            ) + ggtitle("3-color cmap (R, G, B)")).show()

            // With viridis palette
            (letsPlot() + geomImshow(
                greyscaleData,
                cmap = scaleColorViridis().palette(256)
            ) + ggtitle("Viridis palette")).show()

            // With viridis palette and customized colorbar
            (letsPlot() + geomImshow(
                greyscaleData,
                cmap = scaleColorViridis().palette(256),
                cguide = guideColorbar(barWidth = 8, barHeight = 300)
            ) + ggtitle("Viridis + custom colorbar")).show()
        }
    }
}
