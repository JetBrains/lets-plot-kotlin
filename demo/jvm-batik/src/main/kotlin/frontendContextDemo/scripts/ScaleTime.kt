/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.commons.intern.datetime.Duration
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.scale.scaleXTime
import org.jetbrains.letsPlot.scale.scaleYTime
import kotlin.random.Random

object ScaleTime {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Time scale") {
            timePlot("5 seconds", 0..5000 step 25, Duration.MS)
            timePlot("24 hours", 0..24, Duration.HOUR)
            timePlot("24 hours", 0..24, Duration.HOUR, timeAxis = "y")
            timePlot("5 days", 0..120, Duration.HOUR)
            timePlot("30 days", 0..720, Duration.HOUR)
            timePlot("-30..30, MINUTE", -30..30, Duration.MINUTE)
            timePlot("Special: no zero (12 to 30 minutes)", 12..30, Duration.MINUTE)
            timePlot("Special: asymmetric range (-8 to 30 minutes)", -8..30, Duration.MINUTE)
            timePlot("Special: negative (-30..-20, MINUTE)", -30..-20, Duration.MINUTE)
            timePlot("Special: reversed negative (-20..-30, MINUTE)", -20 downTo -30, Duration.MINUTE)
        }
    }

    private fun timePlot(title: String, entries: Iterable<Int>, period: Duration, timeAxis: String = "x") {
        val rnd = Random(0)
        val time = entries.map { it * period.totalMillis }
        val values = time.indices.map { rnd.nextDouble(0.0, 20.0) }

        val data = mapOf<String, Any>(
            "time" to time,
            "values" to values
        )
        val p = ggplot(data) + when (timeAxis) {
            "x" -> geomLine { x = "time"; y = "values" } + scaleXTime(name = title)
            "y" -> geomLine { x = "values"; y = "time" } + scaleYTime(name = title)
            else -> throw IllegalStateException()
        }
        p.show()
    }
}