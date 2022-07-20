/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.datalore.base.datetime.Date
import jetbrains.datalore.base.datetime.DateTime
import jetbrains.datalore.base.datetime.Month
import jetbrains.datalore.base.datetime.tz.TimeZone
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.scale.scaleXDateTime
import org.jetbrains.letsPlot.tooltips.layerTooltips
import kotlin.random.Random

object ScaleDateTime {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("DateTime Scale") {
            val second = 1000.0
            val minute = 60.0 * second
            val hour = 60.0 * minute
            val day = 24.0 * hour

            val instant = TimeZone.UTC.toInstant(DateTime(Date(1, Month.FEBRUARY, 2003)))

            val nDays = 30
            val rnd = Random(0)

            val days = (0..nDays).map { instant.timeSinceEpoch + it * day }
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
                "seconds" to (0..nSeconds).map { instant.timeSinceEpoch + it * second },
                "val" to (0..nSeconds).map { rnd.nextDouble(0.0, 10.0) }
            )

            val t = ggplot(secondsData) +
                    geomLine { x = "seconds"; y = "val" } +
                    scaleXDateTime("Time (min)")
            t.show()
        }
    }
}