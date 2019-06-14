package plotDemo.scripts

import com.jetbrains.datalore.plot.dsl.geom.geom_bar
import com.jetbrains.datalore.plot.dsl.ggplot
import com.jetbrains.datalore.plot.dsl.stat.stat_count
import plotDemo.SwingDemoUtil

object BarGeomAndCountStat {
    @JvmStatic
    fun main(args: Array<String>) {

        val data = mapOf<String, Any>(
            "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
            "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
        )
        val p = ggplot(data)

        // bar (with count stat by default)
        val barLayer = geom_bar {
            x = "cat1"
            fill = "cat2"
        }

        // count stat (with bar geom by default)
        val countLayer = stat_count {
            x = "cat1"
            fill = "cat2"
        }

        // show two frames
        SwingDemoUtil.display(p + barLayer)
        SwingDemoUtil.display(p + countLayer)
    }
}