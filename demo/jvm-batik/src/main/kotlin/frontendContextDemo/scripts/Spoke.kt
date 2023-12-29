package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.coord.coordFixed
import org.jetbrains.letsPlot.geom.geomSpoke
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.tooltips.layerTooltips

object Spoke {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("geomSpoke") {
            val data = mapOf(
                "x" to listOf(0, 1, 1, 0),
                "y" to listOf(0, 0, 1, 1),
                "angle" to listOf(4.7124, 0, 1.5708, 3.1416),
                "radius" to listOf(0.2, 0.4, 0.6, 0.8)
            )

            val p = letsPlot(data) + geomSpoke(size = 5, color = "blue", alpha = 0.5, tooltips = layerTooltips("x")) {
                x = "x"
                y = "y"
                angle = "angle"
                radius = "radius"
            } + coordFixed()
            p.show()
        }
    }
}