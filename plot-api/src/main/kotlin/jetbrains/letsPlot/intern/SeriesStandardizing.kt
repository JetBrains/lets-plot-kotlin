/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import java.time.*
import java.util.*

object SeriesStandardizing {
    @Suppress("SpellCheckingInspection")
    fun isListy(rawValue: Any?) = when (rawValue) {
        is List<*> -> true
        is Iterable<*> -> true
        is Sequence<*> -> true
        is Array<*> -> true
        is ByteArray -> true
        is ShortArray -> true
        is IntArray -> true
        is LongArray -> true
        is FloatArray -> true
        is DoubleArray -> true
        is CharArray -> true
        is Pair<*, *> -> true
        else -> false
    }

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
            is Pair<*, *> -> standardizeList(rawValue.toList())
            else -> throw IllegalArgumentException("Can't transform data[\"$key\"] of type ${rawValue::class.qualifiedName} to a list")
        }
    }

    fun toListOrPass(rawValue: Any): Any {
        if (isListy(rawValue)) {
            return toList("<key not provided>", rawValue)
        }
        return rawValue
    }

    private fun needToStandardizeValues(series: Iterable<*>): Boolean {
        return series.any {
            it != null && !(it is String || it is Double)
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
                    is Number -> it.toDouble()
                    is Char -> it.toString()
                    is Date -> it.time
                    is Instant -> it.toEpochMilli()
                    is ZonedDateTime -> it.toInstant().toEpochMilli()
                    is LocalDate -> noTimeZoneError(it)
                    is LocalTime -> noTimeZoneError(it)
                    is LocalDateTime -> noTimeZoneError(it)
                    is jetbrains.datalore.base.values.Color -> it.toHexColor()
                    is java.awt.Color -> "#%02x%02x%02x".format(it.red, it.green, it.blue)
                    else -> throw IllegalArgumentException("Can't standardize the value \"$it\" of type ${it::class.qualifiedName} as a string, number or date-time.")
                }
            }
        } else {
            series
        }
    }
}