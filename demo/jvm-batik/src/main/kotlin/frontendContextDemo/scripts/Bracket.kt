package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomBracket
import org.jetbrains.letsPlot.geom.geomBracketDodge
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

            run {
                val bracketDodgeData = mapOf(
                    "number of cylinders" to listOf(6, 6, 6, 4, 4, 4),
                    "start" to listOf(0, 0, 1, 0, 0, 1),
                    "end" to listOf(1, 2, 2, 1, 2, 2),
                    "y" to listOf(48, 52, 56, 48, 52, 56),
                    "p" to listOf(0.01, 0.02, 0.03, 0.04, 0.05, 0.06),
                )
                (letsPlot(mpgData) { x = asDiscrete("number of cylinders"); y = "miles per gallon"; fill = "origin of car" }
                  + geomBoxplot()
                  + geomBracketDodge(data = bracketDodgeData)
                      { x = "number of cylinders"; y = "y"; iStart = "start"; iEnd = "end"; label = "p" }).show()
            }
        }
    }
}