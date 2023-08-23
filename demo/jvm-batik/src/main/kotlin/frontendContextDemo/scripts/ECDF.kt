package frontendContextDemo.scripts

import demoData.Iris
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.geomStep
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.stat.statECDF

object ECDF {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("ECDF") {
            val irisData = Iris.map()

            run {
                val p = letsPlot(irisData) { x = "sepal length (cm)" }

                (p + statECDF(pad = true) + ggtitle("Basic demo")).show()
                (p + statECDF(n = 10, pad = true) + ggtitle("Interpolation")).show()
                (p + statECDF { color = "target" } + ggtitle("With additional grouping")).show()


                (letsPlot(irisData) { y = "sepal length (cm)" } +
                        statECDF(pad = true, orientation = "y") +
                        ggtitle("Orientation changed")
                        ).show()
            }

            run {
                // Parameter 'pad'

                val p = letsPlot(irisData) { x = "sepal length (cm)"; color = "target" }

                (p + statECDF() + ggtitle("statECDF()")).show()
                (p + statECDF(pad = true) + ggtitle("statECDF(pad = true)")).show()
                (p + statECDF(pad = false) + ggtitle("statECDF(pad = false)")).show()

                (p + statECDF(geom = Geom.step(pad = true)) + ggtitle("statECDF(geom = Geom.step(pad = true))")).show()
                (p + statECDF(geom = Geom.step(pad = false)) + ggtitle("statECDF(geom = Geom.step(pad = false))")).show()

                (p + statECDF(pad = false, geom = Geom.step(pad = true))
                        + ggtitle("statECDF(pad = false, geom = Geom.step(pad = true))")
                        ).show()

                (p + geomStep(stat = Stat.ecdf()) + ggtitle("geomStep(stat = Stat.ecdf()")).show()
                (p + geomStep(pad = true, stat = Stat.ecdf()) +
                        ggtitle("geomStep(pad = true, stat = Stat.ecdf()")
                        ).show()
                (p + geomStep(pad = false, stat = Stat.ecdf()) +
                        ggtitle("geomStep(pad = false, stat = Stat.ecdf()")
                        ).show()
            }
        }
    }
}