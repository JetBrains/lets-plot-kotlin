/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import java.time.*
import java.util.*

object SeriesStandardizing {
    fun toList(key: String, rawValue: Any): List<Any?> {
        return when (rawValue) {
            is List<*> -> standardizeList(rawValue)
            is Iterable<*> -> standardizeIterable(rawValue).toList()
            is Sequence<*> -> standardizeIterable(rawValue.asIterable()).toList()
            is Array<*> -> standardizeList(rawValue.asList())
            is ByteArray -> standardizeList(rawValue.asList())
            is ShortArray -> standardizeList(rawValue.asList())
            is IntArray -> standardizeList(rawValue.asList())
            is LongArray -> standardizeList(rawValue.asList())
            is FloatArray -> standardizeList(rawValue.asList())
            is DoubleArray -> standardizeList(rawValue.asList())
            is CharArray -> standardizeList(rawValue.asList())
            else -> throw IllegalArgumentException("Can't transform data[\"$key\"] of type ${rawValue::class.qualifiedName} to a list")
        }
    }

    private fun needToStandardizeValues(series: Iterable<*>): Boolean {
        return series.any {
            it != null && !(it is String || it is Number)
        }
    }

    private fun standardizeList(series: List<*>): List<*> {
        // avoid 'toList' on lists (makes copy)
        return standardizeIterable(series) as List<*>
    }

    private fun standardizeIterable(series: Iterable<*>): Iterable<*> {
        fun noTimeZoneError(time: Any): Nothing {
            throw IllegalArgumentException(
                "Can't convert ${time::class.qualifiedName} to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z."
            )
        }
        return if (needToStandardizeValues(series)) {
            series.map {
                when (it) {
                    null -> it
                    is String -> it
                    is Number -> it
                    is Char -> it.toString()
                    is Date -> it.time
                    is Instant -> it.toEpochMilli()
                    is ZonedDateTime -> it.toInstant().toEpochMilli()
                    is LocalDate -> noTimeZoneError(it)
                    is LocalTime -> noTimeZoneError(it)
                    is LocalDateTime -> noTimeZoneError(it)
                    else -> throw IllegalArgumentException("Can't standardize the value \"$it\" of type ${it::class.qualifiedName} as a string, number or date-time.")
                }
            }
        } else {
            series
        }
    }
}