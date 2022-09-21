/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import org.jetbrains.letsPlot.geom.geomLabel
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomText
import org.jetbrains.letsPlot.ggplot


object TextAndLabel {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Texts and labels") {
            val xList = listOf(
                0.0, 0.0, 0.0,
                0.5, 0.5, 0.5,
                1.0, 1.0, 1.0
            )
            val yList = listOf(
                0.0, 0.5, 1.0,
                0.0, 0.5, 1.0,
                0.0, 0.5, 1.0
            )
            val hjustMap = mapOf(0.0 to "left", 0.5 to "center", 1.0 to "right")
            val vjustMap = mapOf(0.0 to "bottom", 0.5 to "middle", 1.0 to "top")
            val hjustNames = xList.map { hjustMap[it] }
            val vjustNames = yList.map { vjustMap[it] }

            val data = mapOf(
                "x" to xList,
                "y" to yList,
                "hjust" to hjustNames,
                "vjust" to vjustNames,
                "label_nums" to xList.zip(yList).map { "${it.first}-${it.second}" },
                "label_text" to hjustNames.zip(vjustNames).map { "${it.first}-${it.second}" }
            )

            val p = ggplot(data) { x = "x"; y = "y" } + geomPoint(size = 3)

            // Use numbers to specify hjust/vjust
            (p + geomText(size = 10) { label = "label_nums"; hjust = "x"; vjust = "y" }).show()

            (p + geomLabel(size = 10, fill = Color.LIGHT_GREEN) {
                label = "label_nums"; hjust = "x"; vjust = "y"
            }).show()

            // Use name ("left", "middle", "right", "bottom", "center", "top") for hjust/vjust
            (p + geomLabel(
                size = 8,
                fontface = "bold",
                color = Color.WHITE,
                labelPadding = 0.5,
                labelR = 0.5,
                labelSize = 0
            ) {
                label = "label_text"; hjust = "hjust"; vjust = "vjust"; fill = "vjust"
            }).show()

        }
    }
}
