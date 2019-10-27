package plotDemo.scripts

import jetbrains.datalorePlot.GlobalSettings
import jetbrains.datalorePlot.geom.geom_tile
import jetbrains.datalorePlot.ggplot
import jetbrains.datalorePlot.scale.scale_fill_gradient2
import plotDemo.SwingJfxFrontendContext
import javax.swing.Timer

object ColorScaleContinuous {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()

        val X = (-64..64).toList()
        val data = mapOf("x" to X)

        val p = ggplot(data) +
                geom_tile(width = 1.0, height = 10.0) {
                    x = "x"
                    color = "x"
                    fill = "x"
                }


        p.show()

        // rainbow
        println("wait for a rainbow ...")
        val timer = Timer(5000) {
            val gradient = scale_fill_gradient2(low = "green", mid = "yellow", high = "red")
            (p + gradient).show()
        }
        timer.isRepeats = false
        timer.start()
    }
}