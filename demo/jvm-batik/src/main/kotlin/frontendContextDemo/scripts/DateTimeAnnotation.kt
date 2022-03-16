/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.datalore.base.datetime.*
import jetbrains.datalore.base.datetime.tz.TimeZone
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.scale.scaleXDateTime
import java.time.Instant.parse
import kotlin.random.Random


object DateTimeAnnotation {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Series Annotations", maxCol = 2) {

            run {
                val instant = TimeZone.UTC.toInstant(DateTime(Date(1, Month.FEBRUARY, 2003)))
                val nDays = 30
                val rnd = Random(0)
                val days = (0..nDays).map { instant.timeSinceEpoch + it * Duration.DAY.duration }.map(::Instant)
                val daysData = mapOf<String, Any>(
                    "days" to days,
                    "val" to (0..nDays).map { rnd.nextDouble(0.0, 20.0) }
                )
                val p = ggplot(daysData) + geomLine { x = "days"; y = "val" }

                (p + scaleXDateTime() + ggtitle("p + scaleXDateTime()")).show()
                (p + ggtitle("Automatic detection of DateTime series")).show()
            }

            run {
                // Java instant
                val dt = listOf(
                    "2021-01-01T00:00:00Z",
                    "2021-02-23T00:00:00Z",
                    "2021-04-03T00:00:00Z",
                    "2021-05-04T00:00:00Z",
                    "2021-10-05T00:00:00Z"
                )

                val instants = dt.map(::parse)
                val daysData = mapOf<String, Any>(
                    "days" to instants,
                    "val" to (1..dt.size)
                )
                val p = ggplot(daysData) + geomLine { x = "days"; y = "val" }

                (p + scaleXDateTime() + ggtitle("p + scaleXDateTime()")).show()
                (p + ggtitle("Automatic detection of DateTime series")).show()
            }
        }
    }
}