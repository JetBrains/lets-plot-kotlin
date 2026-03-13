package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomBracket
import org.jetbrains.letsPlot.letsPlot

object Bracket {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Brackets") {
            val mpgData = AutoMpg.map()

            run {
                val bracketData = mapOf(
                    "min" to listOf("US", "US"),
                    "max" to listOf("Asia", "Europe"),
                    "y" to listOf(48, 51),
                    "p" to listOf(0.01, 0.02),
                )
                (letsPlot(mpgData) { x = "origin of car"; y = "miles per gallon" }
                  + geomBoxplot()
                  + geomBracket(data = bracketData) { xmin = "min"; xmax = "max"; y = "y"; label = "p" }).show()
            }

            run {
                val bracketData = mapOf(
                    "min" to listOf("US", "US"),
                    "max" to listOf("Asia", "Europe"),
                    "y" to listOf(5, 2),
                    "p" to listOf(0.01, 0.02),
                )
                (letsPlot(mpgData) { x = "origin of car"; y = "miles per gallon" }
                  + geomBoxplot()
                  + geomBracket(data = bracketData, lenStart = -0.5, lenEnd = -0.5, tipLengthUnit = "res", vjust = 2)
                      { xmin = "min"; xmax = "max"; y = "y"; label = "p" }).show()
            }
        }
    }
}