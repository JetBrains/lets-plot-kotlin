package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.bistro.waterfall.waterfallPlot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.themes.elementLine
import org.jetbrains.letsPlot.themes.elementText
import org.jetbrains.letsPlot.tooltips.layerTooltips

object Waterfall {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Waterfall plot") {
            run {
                val data = mapOf(
                    "cat" to listOf("A", "B", "C", "D"),
                    "val" to listOf(1, 3, -2, 1)
                )

                val p = waterfallPlot(data, "cat", "val") + ggtitle("Basic demo")
                p.show()
            }

            run {
                val data = mapOf(
                    "cat" to listOf("A", "B", "C", "D", "E"),
                    "val" to listOf(100, 200, -400, 500, -200)
                )

                val p = waterfallPlot(
                    data, "cat", "val",
                    color = "flow_type",
                    fill = "lightgrey",
                    size = 3.0,
                    alpha = 0.75,
                    lineType = "dotted",
                    width = 0.4,
                    showLegend = true,
                    relativeTooltips = layerTooltips()
                        .title("Category: @..xlabel.. (#@..x..)")
                        .minWidth(200)
                        .anchor("top_center")
                        .line("ymax|@..ymax..")
                        .line("ymin|^ymin")
                        .format("@..x..", "d")
                        .format("@..ymax..", ".3f")
                        .format("ymin", "d")
                        .disableSplitting(),
                    calcTotal = false,
                    totalTitle = "result",
                    sortedValue = true,
                    maxValues = 3,
                    hLine = elementLine(color = "magenta", size = 5),
                    hLineOnTop = false,
                    connector = elementLine(color = "cyan", size = 1.5),
                    label = elementText(color = "flow_type", family = "Times", face = "bold", size = 5, angle = 45),
                    labelFormat = "d"
                ) + ggtitle("With parameters")
                p.show()
            }

            run {
                val data = mapOf(
                    "cat" to listOf("A", "B", "C", "D", "T1", "A", "B", "C", null, "E", "T2"),
                    "val" to listOf(1.2, 2.2, -0.4, 1.5, null, -2.0, 1.3, -0.8, 1.0, 1.0, 0.0),
                    "m" to listOf("absolute", "relative", "relative", "relative", "total", "relative", "relative", "relative", "relative", null, "total")
                )

                val p = waterfallPlot(data, "cat", "val", measure = "m", showLegend = true) + ggtitle("With measure")
                p.show()
            }
        }
    }
}