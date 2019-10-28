package plotDemo.scripts

import jetbrains.datalorePlot.GlobalSettings
import jetbrains.datalorePlot.Pos
import jetbrains.datalorePlot.Stat
import jetbrains.datalorePlot.geom.geom_bar
import jetbrains.datalorePlot.geom.geom_point
import jetbrains.datalorePlot.ggplot
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