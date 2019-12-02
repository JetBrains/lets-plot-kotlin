package plotDemo.scripts

import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.lets_plot
import plotDemo.SwingJfxFrontendContext

object Density {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()

        val rand = java.util.Random()
        val data = mapOf<String, Any>(
            "x" to List(500) { rand.nextGaussian() }
        )

        val density = geom_density(color = "red", alpha = 0.3, size = 5.0) { x = "x" }
        val p = lets_plot(data) + density
        p.show()
    }
}