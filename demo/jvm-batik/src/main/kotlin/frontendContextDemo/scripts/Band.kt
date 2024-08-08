package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.coord.coordPolar
import org.jetbrains.letsPlot.geom.geomBand
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot

object Band {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Band") {
            val data = mapOf(
                "hmin" to listOf(-1.5, 0.5),
                "hmax" to listOf(-0.5, 1.5),
                "vmin" to listOf(0, 20),
                "vmax" to listOf(10, 30),
                "cat" to listOf("A", "B"),
            )

            run {
                val p = letsPlot() +
                        geomBand(xmin = -1, xmax = 1) +
                        ggtitle("Basic demo")
                p.show()
            }

            run {
                val p = letsPlot(data) +
                        geomBand(alpha = 0.4, size = 2, linetype = "dashed") { xmin = "hmin"; xmax = "hmax"; fill = "cat" } +
                        geomBand(alpha = 0.4, color = "orange", fill = "yellow") { ymin = "vmin"; ymax = "vmax" } +
                        ggtitle("With parameters")
                p.show()
            }

            run {
                val p = letsPlot(data) +
                        geomBand(fill = "red", alpha = 0.4) { xmin = "hmin"; xmax = "hmax" } +
                        geomBand(fill = "blue", alpha = 0.4) { ymin = "vmin"; ymax = "vmax" } +
                        coordPolar(xlim = Pair(-2, 2), ylim = Pair(-10, 40)) +
                        ggtitle("Polar coordinates")
                p.show()
            }
        }
    }
}