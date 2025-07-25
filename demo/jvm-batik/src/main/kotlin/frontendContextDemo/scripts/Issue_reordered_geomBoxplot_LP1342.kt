package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.letsPlot
import kotlin.random.Random
import java.time.LocalDate as JavaLocalDate

object Issue_reordered_geomBoxplot_LP1342 {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Issue: reordered geomBoxplot LP-1342") {
            val rand = Random(0L)

            val data = mapOf(
                "date" to (0 until 100).map { JavaLocalDate.of(2020, 1, 1).plusDays((it % 10).toLong()).toString() },
                "count" to (0 until 100).map { rand.nextInt(0, 100) }
            )

            run {
                val p = letsPlot(data) +
                        geomBoxplot() { x = "date"; y = "count" }
                p.show()
            }
        }
    }
}