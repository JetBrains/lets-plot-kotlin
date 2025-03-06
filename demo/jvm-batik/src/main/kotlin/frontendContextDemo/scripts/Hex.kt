package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.geomHex
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import kotlin.math.sqrt

object Hex {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Hex") {
            run {
                val data = mapOf<String, Any>(
                    "x" to listOf(-0.5, 0.5, 0),
                    "y" to listOf(0, 0, 1.0 / HEX_HEIGHT),
                    "g" to listOf("a", "b", "c")
                )
                val p = letsPlot(data) +
                        geomHex(stat = Stat.identity) { x = "x"; y = "y"; fill = "g" } +
                        ggtitle("Basic hex plot\nstat = identity")
                p.show()
            }

            run {
                val data = mapOf<String, Any>(
                    "x" to listOf(-1, -1, 1, 0.95, 1.05, 0.0, 1.0, -0.5),
                    "y" to listOf(-1.0, 1.0, -1.0, 0.95, 1.05, 1.0 / 3.0, 1.0 / 3.0, 0.0).map { it / HEX_HEIGHT }
                )
                val p = letsPlot(data) { x = "x"; y = "y" } +
                        geomHex(binWidth = Pair(1.0, 1.0)) +
                        geomPoint(shape = 21, size = 4, color = "black", fill = "orange") +
                        ggtitle("Basic hex plot\nstat = default")
                p.show()
            }
        }
    }

    private val HEX_HEIGHT = 2.0 / sqrt(3.0) // height of right hexagon with width = 1
}