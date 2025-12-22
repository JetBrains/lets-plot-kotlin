/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.geom.geomStep
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.scaleYTime
import org.jetbrains.letsPlot.tooltips.layerTooltips
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

object DurationDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Duration support") {
            kotlinDurationPlot()
            javaDurationPlot()
        }
    }

    private fun kotlinDurationPlot() {
        // Example using kotlin.time.Duration
        val tasks = listOf("Task A", "Task B", "Task C", "Task D", "Task E")
        val durations = listOf(
            2.hours + 30.minutes,
            1.hours + 45.minutes,
            3.hours + 15.minutes,
            45.minutes,
            2.hours
        )

        val data = mapOf(
            "task" to tasks,
            "duration" to durations
        )

        val p = ggplot(data) +
                geomBar(stat = org.jetbrains.letsPlot.Stat.identity) {
                    x = "task"
                    y = "duration"
                } +
                scaleYTime() +
                ggtitle("Task Durations (kotlin.time.Duration)")

        p.show()
    }

    private fun javaDurationPlot() {
        // Example using java.time.Duration
        val executionTimes = (0..10).map { it * 0.5 }
        val durations = executionTimes.map {
            java.time.Duration.ofMinutes((it * 60).toLong()).plusSeconds((it * 30 % 60).toLong())
        }

        val data = mapOf(
            "time" to executionTimes,
            "duration" to durations
        )

        val p = ggplot(data) +
                geomStep(
                    tooltips = layerTooltips().line("T=^y")
                ) {
                    x = "time"
                    y = "duration"
                } +
                scaleYTime() +
                ggtitle("Process Duration Over Time (java.time.Duration)")

        p.show()
    }
}
