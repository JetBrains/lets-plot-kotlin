package plotDemo.scripts

import jetbrains.datalorePlot.GlobalSettings
import jetbrains.datalorePlot.geom.geom_histogram
import jetbrains.datalorePlot.ggplot
import plotDemo.SwingJfxFrontendContext

object Histogram {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()

        val rand = java.util.Random()
        val data = mapOf<String, Any>(
            "x" to List(500) { rand.nextGaussian() } + List(500) { rand.nextGaussian() + 1.0 },
            "c" to List(500) { "A" } + List(500) { "B" }
        )

        val geom = geom_histogram(alpha = 0.3, size = 0.0) { x = "x"; fill = "c" }
        val p = ggplot(data) + geom
        p.show()
    }
}