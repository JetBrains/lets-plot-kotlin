package plotDemo.scripts

import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.scale.scale_size_area
import plotDemo.SwingJfxFrontendContext

object SizeAreaScale {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalSettings.frontendContext = SwingJfxFrontendContext()
        val dat = mapOf<String, Any>(
            "x" to (0..5).map { it },
            "y" to (0..5).map { 0 },
            "s" to (0..5).map { it }
        )

        val p = ggplot(dat) +
                geom_point { x = "x"; y = "y"; size = "s" } +
                scale_size_area(max_size = 70, guide = "none")

        p.show()
    }
}