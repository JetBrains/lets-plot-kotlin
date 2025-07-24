/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
import org.jetbrains.letsPlot.commons.intern.datetime.Date
import org.jetbrains.letsPlot.commons.intern.datetime.DateTime
import org.jetbrains.letsPlot.commons.intern.datetime.Instant
import org.jetbrains.letsPlot.commons.intern.datetime.Time

internal object Standardizing {

    fun standardizeValue(value: Any?): Any? {
        return when (value) {
            null -> value
            is String -> value
            is Number -> toDouble(value)
            is Char -> value.toString()
            is org.jetbrains.letsPlot.commons.values.Color -> value.toHexColor()
            is Map<*, *> -> MapStandardizing.standardize(value)
            is Enum<*> -> value.name
            // Kotlinx DateTime API
            is kotlinx.datetime.Instant -> value.toEpochMilliseconds().toDouble()
            is kotlinx.datetime.LocalDate -> value.atStartOfDayIn(kotlinx.datetime.TimeZone.UTC)
                .toEpochMilliseconds().toDouble()

            is kotlinx.datetime.LocalTime -> {
                ((value.hour * 3600 + value.minute * 60 + value.second) * 1000L + value.nanosecond / 1_000_000)
                    .toDouble()
            }

            is kotlinx.datetime.LocalDateTime -> value.toInstant(kotlinx.datetime.TimeZone.UTC)
                .toEpochMilliseconds().toDouble()

            // Lets-Plot DateTime API
            is Instant -> throw IllegalArgumentException("Use java.util.Instant or kotlinx.datetime.Instant instead of org.jetbrains.letsPlot.commons.intern.datetime.Instant")
            is DateTime -> throw IllegalArgumentException("Use java.util.LocalDateTime or kotlinx.datetime.LocalDateTime instead of org.jetbrains.letsPlot.commons.intern.datetime.DateTime")
            is Date -> throw IllegalArgumentException("Use java.util.LocalDate or kotlinx.datetime.LocalDate instead of org.jetbrains.letsPlot.commons.intern.datetime.Date")
            is Time -> throw IllegalArgumentException("Use java.util.LocalTime or kotlinx.datetime.LocalTime instead of org.jetbrains.letsPlot.commons.intern.datetime.Time")

            else -> {
                if (JvmStandardizing.isJvm(value)) {
                    JvmStandardizing.standardize(value)
                } else if (SeriesStandardizing.isListy(value)) {
                    val l = SeriesStandardizing.toList(value)
                    l.map { standardizeValue(it) }
                } else {
                    // This might be some ligit object like `org.jetbrains.letsPlot.MappingMeta` for example.
                    value
                }
            }
        }
    }

    private fun toDouble(n: Number): Double? {
        return when (n) {
            is Float -> if (n.isFinite()) n.toDouble() else null
            is Double -> if (n.isFinite()) n else null
            else -> n.toDouble()
        }
    }

}