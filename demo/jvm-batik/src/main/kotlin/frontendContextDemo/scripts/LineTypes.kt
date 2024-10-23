package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomSegment
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleLinetypeIdentity
import org.jetbrains.letsPlot.tooltips.tooltipsNone

object LineTypes {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Line types") {

            fun plot(linetypes: List<Any>): Plot {
                val n = linetypes.size
                val data = mapOf(
                    "x" to List(n) { 0 },
                    "xend" to List(n) { 1 },
                    "y" to linetypes,
                    "yend" to linetypes,
                    "linetype" to linetypes
                )

                return letsPlot(data) +
                        geomSegment(size = 3, tooltips = tooltipsNone) {
                            x = "x"; y = "y"; xend = "xend"; yend = "yend"; linetype = "linetype"
                        } +
                        scaleLinetypeIdentity()
            }

            plot(
                listOf(
                    "solid",
                    "dashed",
                    "dotted",
                    "dotdash",
                    "longdash",
                    "twodash"
                )
            ).show()

            plot(
                listOf(
                    listOf(1, 1),
                    listOf(5, 5),
                    listOf(10, 5),
                    5 to listOf(10, 5),
                    listOf(5, 10, 1, 10),
                    listOf(10, 5, 1, 5, 1, 5)
                )
            ).show()

            plot(
                listOf(
                    "11",
                    "55",
                    "A5",
                    "5A1A"
                )
            ).show()
        }
    }

}