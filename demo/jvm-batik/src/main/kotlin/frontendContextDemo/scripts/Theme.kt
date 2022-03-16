/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.elementBlank
import jetbrains.letsPlot.elementText
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.theme
import jetbrains.letsPlot.themeGrey

object Theme {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Theme", maxCol = 2) {
            val xs = (-64..64).toList()
            val data = mapOf("x" to xs)

            val p = ggplot(data) +
                    geomTile(width = 1.0, height = 10.0) { x = "x"; color = "x"; fill = "x" }

            (p + theme().legendPositionNone())
                .show()

            // compose themes --> the same
            (p + themeGrey() + theme().legendPositionNone())
                .show()

            (p + theme(
                axisTextY = "blank",
                axisTicksY = elementBlank(),
                axisTitleY = "blank"
            ).legendPositionNone()).show()

            // Add plot labels and use colors
            (p + labs(
                title = "The plot title",
                subtitle = "The plot subtitle",
                caption = "The plot caption"
            ) + theme(
                plotTitle = elementText(Color.DARK_BLUE),
                plotSubtitle = elementText(Color.GRAY),
                plotCaption = elementText(Color.DARK_GREEN)
            ).legendPositionNone())
                .show()
        }
    }
}
