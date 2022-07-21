/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.datalore.base.values.Color
import org.jetbrains.letsPlot.coord.coordFixed
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.themes.*
import org.jetbrains.letsPlot.tooltips.layerTooltips
import java.awt.Dimension

object Theme {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Theme", maxCol = 2, plotSize = Dimension(600, 240)) {
            run {
                val xs = (-64..64).toList()
                val data = mapOf("x" to xs)

                val p = ggplot(data) +
                        geomTile(width = 1.0, height = 10.0) { x = "x"; color = "x"; fill = "x" }

                (p + theme().legendPositionNone())
                    .show()

                // compose themes --> the same
                (p + themeGrey() + theme()
                    .legendPositionNone())
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

            run {
                val data = mapOf(
                    "x" to (0..2).toList()
                )

                val p = ggplot(data) {
                    x = "x"
                    size = "x"
                } + geomPoint(
                    tooltips = layerTooltips()
                        .title("Title")
                        .line("label|value")
                        .line("Static text")
                ) + labs(
                    x = "X axis title",
                    y = "Y axis title",
                    size = "Legend title",
                ) + coordFixed(ylim = -0.3 to 0.3)

                (p + ggtitle("Default")).show()

                // Change font faces
                val fontFaceOpts = theme(
                    plotTitle = elementText(face = "bold_italic"),
                    legendTitle = elementText(face = "bold"),
                    legendText = elementText(face = "italic"),
                    axisTitle = elementText(face = "bold"),
                    axisText = elementText(face = "bold_italic"),
                    tooltipText = elementText(face = "italic")
                )
                (p + fontFaceOpts + ggtitle("Change font faces")).show()


                // Change tooltip colors
                val tooltipOpts = theme(
                    tooltip = elementRect(fill = "#238b45", color = "#225e32", size = 2),
                    tooltipText = elementText(color = "#bae4b3"),
                    tooltipTitleText = elementText(color = "#edf8e9"),
                    axisTooltipTextX = elementText(color = "green")
                )
                (p + tooltipOpts + ggtitle("Change tooltip colors")).show()

                // Add panel background and border
                (p + ggtitle("Borders") + theme(
                    panelBackground = elementRect(
                        fill = "#f0f8ff",
                        color = "#9e9eff",
                        size = 5
                    ),
                    panelBorder = elementRect(color = "white", size = 2),
                    plotBackground = elementRect(color = "black", size = 2)
                )).show()
            }
        }
    }
}
