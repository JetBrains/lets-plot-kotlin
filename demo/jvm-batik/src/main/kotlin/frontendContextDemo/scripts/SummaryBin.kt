package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.stat.statSummaryBin

object SummaryBin {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Summary") {
            val data = getData()

            run {
                val statLayer = statSummaryBin {
                    x = "x"; y = "y"
                }
                val p = ggplot(data) + statLayer + ggtitle("Basic demo")
                p.show()
            }
        }
    }

    private fun getData(n: Int = 100): Map<String, Any> {
        val rand = java.util.Random(42)
        return mapOf<String, Any>(
            "x" to List(n) { rand.nextGaussian() },
            "y" to List(n) { rand.nextGaussian() }
        )
    }
}