/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation
import java.time.*
import java.util.*

private val AWT_PRESENT: Boolean = try {
    @Suppress("KotlinConstantConditions")
    java.awt.Color.WHITE == java.awt.Color.WHITE
} catch (_: NoClassDefFoundError) {
    false     // Android
}

actual object JvmStandardizing {
    actual fun isJvm(o: Any): Boolean {
        if (AWT_PRESENT && o is java.awt.Color) return true

        return when (o) {
            is Date -> true
            is Instant -> true
            is ZonedDateTime -> true
            is OffsetDateTime -> true
            is LocalDate -> true
            is LocalTime -> true
            is LocalDateTime -> true
            else -> false
        }
    }

    actual fun getTypeAnnotation(o: Any): String {
        return when (o) {
            is Date -> SeriesAnnotation.Types.DATE_TIME
            is Instant -> SeriesAnnotation.Types.DATE_TIME
            is ZonedDateTime -> SeriesAnnotation.Types.DATE_TIME
            is OffsetDateTime -> SeriesAnnotation.Types.DATE_TIME
            is LocalDate -> SeriesAnnotation.Types.DATE
            is LocalTime -> SeriesAnnotation.Types.TIME
            is LocalDateTime -> SeriesAnnotation.Types.DATE_TIME
            else -> SeriesAnnotation.Types.UNKNOWN
        }
    }

    actual fun getTimeZoneAnnotation(o: Any): String? {
        return when (o) {
            is ZonedDateTime -> if (o.zone.id == "Z") "UTC" else o.zone.id
            is OffsetDateTime -> if (o.offset.id == "Z") "UTC" else "UTC" + o.offset.id
            else -> null
        }
    }

    actual fun standardize(o: Any): Any {
        if (AWT_PRESENT && o is java.awt.Color) {
            return "#%02x%02x%02x".format(o.red, o.green, o.blue)
        }

        return when (o) {
            is Date -> o.time
            is Instant -> o.toEpochMilli()
            is ZonedDateTime -> o.toInstant().toEpochMilli()
            is OffsetDateTime -> o.toInstant().toEpochMilli()
            is LocalDate -> o.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
            is LocalTime -> LocalDateTime.of(LocalDate.EPOCH, o).toInstant(ZoneOffset.UTC).toEpochMilli()
            is LocalDateTime -> o.toInstant(ZoneOffset.UTC).toEpochMilli()
            else -> {
                throw IllegalArgumentException(
                    "Can't standardize value \"$o\" of type ${o::class.qualifiedName} as string or number."
                )
            }
        }.toDouble()
    }
}