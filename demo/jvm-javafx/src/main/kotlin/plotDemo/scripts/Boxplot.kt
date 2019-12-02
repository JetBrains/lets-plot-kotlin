package plotDemo.scripts

import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.geom.geom_boxplot
import jetbrains.letsPlot.lets_plot
import plotDemo.SwingJfxFrontendContext
import kotlin.math.abs

object Boxplot {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()

        val categories = listOf("A", "B", "C", "D", "E")

        val n = 500
        val rand = java.util.Random()
        val data = mapOf<String, Any>(
            "val" to List(n) { rand.nextGaussian() },
            "cat" to List(n) { categories[abs(rand.nextInt()).rem(categories.size)] }
        )

        val p = lets_plot(data) { x = "cat"; y = "val" } + geom_boxplot(outlierColor = "red")
        p.show()
    }
}
