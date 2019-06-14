package plotDemo.scripts

import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.ggplot
import plotDemo.SwingDemoUtil

object Scatter {
    @JvmStatic
    fun main(args: Array<String>) {

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

        SwingDemoUtil.display(p)
    }
}