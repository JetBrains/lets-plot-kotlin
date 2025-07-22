/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import java.time.*
import java.util.*

private val AWT_PRESENT: Boolean = try {
    @Suppress("KotlinConstantConditions")
    java.awt.Color.WHITE == java.awt.Color.WHITE
} catch (_: NoClassDefFoundError) {
    false     // Android
}

actual object JvmStandardizing {
    actual fun isDateTimeJvm(o: Any): Boolean {
        return when (o) {
            is Date -> true
            is Instant -> true
            is ZonedDateTime -> true
            is LocalDate -> true
            is LocalTime -> true
            is LocalDateTime -> true
            else -> false
        }
    }

    actual fun isJvm(o: Any): Boolean {
        return when {
            isDateTimeJvm(o) -> true
            AWT_PRESENT && o is java.awt.Color -> true
            else -> false
        }
    }

    actual fun standardize(o: Any): Any {
        if (AWT_PRESENT &&
            o is java.awt.Color
        ) {
            return "#%02x%02x%02x".format(o.red, o.green, o.blue)
        }

        return when (o) {
            is Date -> o.time.toDouble()
            is Instant -> o.toEpochMilli().toDouble()
            is ZonedDateTime -> o.toInstant().toEpochMilli().toDouble()
            is LocalDate -> o.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli().toDouble()
            is LocalTime -> LocalDateTime.of(LocalDate.EPOCH, o).toInstant(ZoneOffset.UTC).toEpochMilli().toDouble()
            is LocalDateTime -> o.toInstant(ZoneOffset.UTC).toEpochMilli().toDouble()
            else -> unsupportedTypeError(o)
        }
    }

    private fun unsupportedTypeError(o: Any) {
        throw IllegalArgumentException("Can't standardize value \"$o\" of type ${o::class.qualifiedName} as string or number.")
    }
}