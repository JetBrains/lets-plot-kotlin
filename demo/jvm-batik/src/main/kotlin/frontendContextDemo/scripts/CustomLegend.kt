package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomLabel
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomRect
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.aesOverrides
import org.jetbrains.letsPlot.scale.layerKey

object CustomLegend {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Custom legend") {
            (letsPlot() +
                    geomLine(
                        data = mapOf("x" to listOf(0, 10), "y" to listOf(1, 1)),
                        color = "blue", size = 1.2, linetype = "dotted",
                        manualKey = "Blue zone"
                    ) { x = "x"; y = "y" } +
                    geomPoint(x = 5, y = 0, color = "red", size = 5, manualKey = "Red zone") +
                    geomRect(
                        xmin = 2,
                        xmax = 8,
                        ymin = 0.2,
                        ymax = 0.8,
                        alpha = 0.2,
                        fill = "green",
                        manualKey = "Green zone"
                    ) +
                    geomLabel(
                        x = 8,
                        y = 0,
                        label = "Text",
                        fill = "orange",
                        color = "white",
                        size = 8,
                        manualKey = "Orange zone"
                    )
                    ).show()

            (letsPlot() +
                    geomLine(
                        data = mapOf("x" to listOf(0, 10), "y" to listOf(1, 1)),
                        color = "blue", size = 1.2, linetype = "dotted",
                        manualKey = layerKey("Blue zone", index = 2)
                    ) { x = "x"; y = "y" } +
                    geomPoint(
                        x = 5, y = 0, color = "red", size = 5,
                        manualKey = layerKey(
                            "Red zone", index = 0,
                            aes = aesOverrides(size = 7, shape = 21)
                        )
                    ) +
                    geomRect(
                        xmin = 2, xmax = 8, ymin = 0.2, ymax = 0.8, alpha = 0.2, fill = "green",
                        manualKey = layerKey("Green zone", index = 1, aes = aesOverrides(alpha = 0.8))
                    ) +
                    geomLabel(
                        x = 8, y = 0, label = "Text", fill = "orange", color = "white", size = 8,
                        manualKey = layerKey("Orange zone", aes = aesOverrides(color = "black"))
                    )
                    ).show()
        }
    }
}