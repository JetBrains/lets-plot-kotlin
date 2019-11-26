package plotDemo.scripts

import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import plotDemo.SwingJfxFrontendContext

object Scatter {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()

        val data = mapOf<String, Any>(
            "weight" to listOf(68, 84, 73),
            "height" to listOf(175, 169, 180),
            "shoe" to listOf(42, 40, 45)
        )


        val p = ggplot(data) +
                geom_point(color = "red") {
                    x = "weight"
                    y = "height"
                    size = "shoe"
                }

        p.show()
    }
}