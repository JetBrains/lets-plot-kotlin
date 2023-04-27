/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.RasterData
import org.jetbrains.letsPlot.geom.geomImshow
import org.jetbrains.letsPlot.letsPlot

object Imshow {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Imshow") {

            // Cases taken from org.jetbrains.letsPlot.ImshowTest
            val imdataList = listOf(
                // simple test
                RasterData.create(
                    listOf(
                        listOf(listOf(150, 0, 0), listOf(0, 150, 0)),
                        listOf(listOf(0, 0, 150), listOf(150, 150, 0)),
                    )
                ),

                // gray 2 x 3 matrix of byte
                RasterData.create(
                    listOf(
                        listOf(0.toByte(), 0.toByte(), 0.toByte()),
                        listOf(255.toByte(), 255.toByte(), 255.toByte())
                    )
                )
            )

            imdataList.forEach {
                (letsPlot() + geomImshow(it)).show()
            }
        }
    }
}
