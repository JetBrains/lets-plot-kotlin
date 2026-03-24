/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotApiDemo.scripts

import plotApiDemo.ScriptInSwingContext
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.scale.scaleXDateTime
import org.jetbrains.letsPlot.tooltips.layerTooltips
import kotlin.random.Random
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.seconds

object ScaleDateTime {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInSwingContext.eval("DateTime Scale") {
            val startMillis = LocalDateTime(2003, 2, 1, 0, 0)
                .toInstant(TimeZone.UTC)
                .toEpochMilliseconds()

            val nDays = 30
            val rnd = Random(0)

            val days = (0..nDays).map { startMillis + it * 1.days.inWholeMilliseconds }
            val daysData = mapOf<String, Any>(
                "days" to days,
                "val" to (0..nDays).map { rnd.nextDouble(0.0, 20.0) }
            )
            val p = ggplot(daysData) + geomLine { x = "days"; y = "val" }

            // Default
            (p + scaleXDateTime()).show()

            // Format axis labels
            (p + scaleXDateTime(format = "%e %B")).show()

            // Format axis labels for breaks specified manually
            val breaks = days.slice(0..nDays step 15)
            (p + scaleXDateTime(format = "%e %B", breaks = breaks)).show()

            // Format value shown in the tooltip
            (ggplot(daysData) +
                    geomLine(
                        tooltips = layerTooltips()
                            .line("@val [@days]")
                            .format("days", "%d.%m.%Y")
                            .anchor("top_left")
                    ) { x = "days"; y = "val" } +
                    scaleXDateTime(format = "%e %B")
                    ).show()


            val nSeconds = 1000
            val secondsData = mapOf<String, Any>(
                "seconds" to (0..nSeconds).map { startMillis + it * 1.seconds.inWholeMilliseconds },
                "val" to (0..nSeconds).map { rnd.nextDouble(0.0, 10.0) }
            )

            val t = ggplot(secondsData) +
                    geomLine { x = "seconds"; y = "val" } +
                    scaleXDateTime("Time (min)")
            t.show()
        }
    }
}