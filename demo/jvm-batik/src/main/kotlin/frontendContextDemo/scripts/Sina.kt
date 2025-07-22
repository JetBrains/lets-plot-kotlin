package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.geom.geomSina
import org.jetbrains.letsPlot.geom.geomViolin
import org.jetbrains.letsPlot.letsPlot

object Sina {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Sina plot") {
            val mpgData = AutoMpg.map()

            run {
                (letsPlot(mpgData) { x = "origin of car"; y = "miles per gallon" }
                 + geomViolin()
                 + geomSina(seed = 42)).show()
            }

            run {
                (letsPlot(mpgData) { x = "origin of car"; y = "miles per gallon" }
                 + geomViolin(showHalf = -1) { fill = "origin of car" }
                 + geomSina(showHalf = 1, seed = 42) { color = "origin of car" }).show()
            }

            run {
                (letsPlot(mpgData) { x = asDiscrete("number of cylinders"); y = "miles per gallon" }
                 + geomViolin(alpha = .25) { fill = "origin of car" }
                 + geomSina(shape = 21, size = 1.2, stroke = .4, color = "black", seed = 42) { fill = "origin of car" }).show()
            }
        }
    }
}