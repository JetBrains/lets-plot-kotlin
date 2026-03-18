/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInSwingContext
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.scale.scaleXTime
import org.jetbrains.letsPlot.scale.scaleYTime
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes

object ScaleTime {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInSwingContext.eval("Time scale") {
            timePlot("5 seconds", 0..5000 step 25, 1.milliseconds)
            timePlot("24 hours", 0..24, 1.hours)
            timePlot("24 hours", 0..24, 1.hours, timeAxis = "y")
            timePlot("5 days", 0..120, 1.hours)
            timePlot("30 days", 0..720, 1.hours)
            timePlot("-30..30, MINUTE", -30..30, 1.minutes)
            timePlot("Special: no zero (12 to 30 minutes)", 12..30, 1.minutes)
            timePlot("Special: asymmetric range (-8 to 30 minutes)", -8..30, 1.minutes)
            timePlot("Special: negative (-30..-20, MINUTE)", -30..-20, 1.minutes)
            timePlot("Special: reversed negative (-20..-30, MINUTE)", -20 downTo -30, 1.minutes)
        }
    }

    private fun timePlot(title: String, entries: Iterable<Int>, period: Duration, timeAxis: String = "x") {
        val rnd = Random(0)
        val time = entries.map { it * period.inWholeMilliseconds }
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