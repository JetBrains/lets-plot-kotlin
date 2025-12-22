/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import java.time.*
import java.util.*

actual object StandardizingTestJvmValues {
    actual fun getTestValues(): List<Any> {
        val zonedDateTime = ZonedDateTime.of(2023, 1, 1, 12, 30, 45, 0, ZoneId.of("UTC"))
        val instant = zonedDateTime.toInstant()
        val localDate = zonedDateTime.toLocalDate()
        val localTime = zonedDateTime.toLocalTime()
        val localDateTime = zonedDateTime.toLocalDateTime()
        val date = Date.from(instant)
        val offsetDateTime: OffsetDateTime = zonedDateTime.toOffsetDateTime()
        val duration = Duration.ofHours(2).plusMinutes(30).plusSeconds(45)

        return listOf(
            zonedDateTime,
            instant,
            localDate,
            localTime,
            localDateTime,
            date,
            offsetDateTime,
            duration,
        )
    }

    actual fun getExpectedValues(): List<Any> {
        // Expected timestamp for 2023-01-01T12:30:45Z
        val expectedTimestamp = 1672576245000L

        val expectedLocalDateTimestamp = ZonedDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))
            .toInstant().toEpochMilli()

        val expectedLocalTimeTimestamp = ZonedDateTime.of(1970, 1, 1, 12, 30, 45, 0, ZoneId.of("UTC"))
            .toInstant().toEpochMilli()

        val expectedDurationMillis = Duration.ofHours(2).plusMinutes(30).plusSeconds(45).toMillis()

        return getTestValues().map {
            when (it) {
                is LocalDate -> expectedLocalDateTimestamp
                is LocalTime -> expectedLocalTimeTimestamp
                is LocalDateTime -> expectedTimestamp // Same as ZonedDateTime because here we use UTC
                is Duration -> expectedDurationMillis
                else -> expectedTimestamp
            }.toDouble()
        }
    }
}