/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import java.time.*
import java.util.*

actual object JvmStandardizing {
    actual fun isDateTimeJvm(o: Any): Boolean {
        return when (o) {
            is Date -> true
            is Instant -> true
            is ZonedDateTime -> true
            is LocalDate -> true
            is LocalTime -> true
            is LocalDateTime -> true
            else -> when {
                isKotlinxDateTime(o) -> true
                else -> false
            }
        }
    }

    actual fun isJvm(o: Any): Boolean {
        return when {
            isDateTimeJvm(o) -> true
            o is java.awt.Color -> true
            else -> false
        }
    }

    actual fun standardize(o: Any): Any {
        return when (o) {
            is Date -> o.time
            is Instant -> o.toEpochMilli()
            is ZonedDateTime -> o.toInstant().toEpochMilli()
            is LocalDate -> noTimeZoneError(o)
            is LocalTime -> noTimeZoneError(o)
            is LocalDateTime -> noTimeZoneError(o)
            is java.awt.Color -> "#%02x%02x%02x".format(o.red, o.green, o.blue)
            else -> when {
                isKotlinxDateTime(o) -> when (o) {
                    is kotlinx.datetime.Instant -> o.toEpochMilliseconds()
                    is kotlinx.datetime.LocalDate -> noTimeZoneError(o)
                    is kotlinx.datetime.LocalDateTime -> noTimeZoneError(o)
                    else -> unsupportedTypeError(o)
                }
                else -> unsupportedTypeError(o)
            }
        }
    }

    private fun isKotlinxDateTime(o: Any) = o.javaClass.name.startsWith("kotlinx.datetime.")

    private fun noTimeZoneError(time: Any): Nothing {
        throw IllegalArgumentException(
            "Can't convert ${time::class.qualifiedName} to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z."
        )
    }

    private fun unsupportedTypeError(o: Any) {
        throw IllegalArgumentException("Can't standardize value \"$o\" of type ${o::class.qualifiedName} as string or number.")
    }
}