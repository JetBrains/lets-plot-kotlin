package plotDemo.scripts

import com.jetbrains.datalore.plot.dsl.Pos
import com.jetbrains.datalore.plot.dsl.Stat
import com.jetbrains.datalore.plot.dsl.geom.geom_bar
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.ggplot
import plotDemo.SwingDemoUtil

object PointsOverBars {
    @JvmStatic
    fun main(args: Array<String>) {

        val data = mapOf<String, Any>(
            "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
            "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
        )
        val p = ggplot(data)

        // bars
        val barLayer = geom_bar {
            x = "cat1"
            fill = "cat2"
        }

        // points with count stat and `stack` position adjustment
        val pointLayer = geom_point(stat = Stat.count(), position = Pos.stack, size = 15) {
            x = "cat1"
            color = "cat2"
        }

        SwingDemoUtil.display(p + barLayer + pointLayer)
    }
}