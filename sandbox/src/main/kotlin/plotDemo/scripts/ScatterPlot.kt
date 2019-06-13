package plotDemo.scripts

import com.jetbrains.datalore.plot.Plot
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.ggplot
import plotDemo.DemoScriptBase

class ScatterPlot : DemoScriptBase() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ScatterPlot().display(plot())
        }

        private fun plot(): Plot {
            val data = mapOf<String, Any>(
                "weight" to listOf(68, 84, 73),
                "height" to listOf(175, 169, 180),
                "shoe" to listOf(42, 40, 45)
            )

            return ggplot(data) +
                    geom_point({
                        x = "weight"
                        y = "height"
                        size = "shoe"
                    }, color = "red")
        }
    }
}