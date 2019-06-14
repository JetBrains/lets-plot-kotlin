package plotDemo.scripts

import com.jetbrains.datalore.plot.dsl.geom.geom_tile
import com.jetbrains.datalore.plot.dsl.ggplot
import plotDemo.SwingDemoUtil

object ColorScaleContinuous {
    @JvmStatic
    fun main(args: Array<String>) {

        val X = (-64..64).toList()
        val data = mapOf("x" to X)

        val p = ggplot(data) +
                geom_tile(width = 1.0, height = 10.0) {
                    x = "x"
                    color = "x"
                    fill = "x"
                }


        SwingDemoUtil.display(p)
    }
}