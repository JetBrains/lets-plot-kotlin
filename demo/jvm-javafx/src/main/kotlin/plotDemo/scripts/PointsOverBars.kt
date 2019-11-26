package plotDemo.scripts

import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import plotDemo.SwingJfxFrontendContext

object PointsOverBars {
    @JvmStatic
    @Suppress("DuplicatedCode")
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()

        val data = mapOf<String, Any>(
            "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
            "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
        )
        val p = ggplot(data)

        // bars
        val bars = geom_bar {
            x = "cat1"
            fill = "cat2"
        }

        // points with count stat and `stack` position adjustment
        val points = geom_point(stat = Stat.count(), position = Pos.stack, size = 15.0) {
            x = "cat1"
            color = "cat2"
        }

        (p + bars + points).show()
    }
}