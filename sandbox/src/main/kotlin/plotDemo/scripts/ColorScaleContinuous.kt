package plotDemo.scripts

import com.jetbrains.datalore.plot.dsl.geom.geom_tile
import com.jetbrains.datalore.plot.dsl.ggplot
import com.jetbrains.datalore.plot.dsl.scale.scale_fill_gradient2
import plotDemo.SwingDemoUtil
import javax.swing.Timer

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

        // rainbow
        println("wait for a rainbow ...")
        val timer = Timer(5000) {

            val gradient = scale_fill_gradient2(low = "green", mid = "yellow", high = "red")
            SwingDemoUtil.display(p + gradient)

        }
        timer.isRepeats = false
        timer.start()
    }
}