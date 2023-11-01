package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.geom.*
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.pos.positionDodgeV

object HorizontalGeoms {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Horizontal geoms") {
            val data = mapOf<String, Any>(
                "supp" to listOf("OJ", "OJ", "OJ", "VC", "VC", "VC"),
                "dose" to listOf(0.5, 1.0, 1.5, 0.5, 1.0, 1.5),
                "len" to listOf(13.23, 21.7, 27.06, 7.00, 17.77, 25.14),
                "min" to listOf(10.00, 20.8, 24.0, 5.24, 15.26, 24.35),
                "max" to listOf(15.00, 24.6, 28.11, 10.72, 18.28, 27.93)
            )

            /*
            val p = letsPlot(data) {
                y = "dose"
                x = "len"
                xmin = "min"
                xmax = "max"
                color = "supp"
            }
            (p + geomErrorBar(position = positionDodgeV(0.95)) + ggtitle("ErrorBar")).show()
            (p + geomCrossbar(position = positionDodgeV(0.95)) + ggtitle("Crossbar")).show()
            (p + geomPointRange(position = positionDodgeV(0.95)) + ggtitle("PointRange")).show()
            (p + geomLineRange(position = positionDodgeV(0.95)) + ggtitle("LineRange")).show()
            (p + geomRibbon() + ggtitle("Ribbon")).show()
            */

            // mapping in geom
            (letsPlot(data) + geomErrorBar(position = positionDodgeV(0.95)) {
                y = "dose"
                x = "len"
                xmin = "min"
                xmax = "max"
                color = "supp"
            } + ggtitle("ErrorBar")).show()

            (letsPlot(data) + geomCrossbar(position = positionDodgeV(0.95)) {
                y = "dose"
                x = "len"
                xmin = "min"
                xmax = "max"
                color = "supp"
            } + ggtitle("Crossbar")).show()

            (letsPlot(data) + geomPointRange(position = positionDodgeV(0.95)) {
                y = "dose"
                x = "len"
                xmin = "min"
                xmax = "max"
                color = "supp"
            } + ggtitle("PointRange")).show()

            (letsPlot(data) + geomLineRange(position = positionDodgeV(0.95)) {
                y = "dose"
                x = "len"
                xmin = "min"
                xmax = "max"
                color = "supp"
            } + ggtitle("LineRange")).show()

            (letsPlot(data) + geomRibbon() {
                y = "dose"
                x = "len"
                xmin = "min"
                xmax = "max"
                color = "supp"
            } + ggtitle("Ribbon")).show()
        }
    }
}