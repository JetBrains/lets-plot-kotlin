/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import org.jetbrains.letsPlot.intern.detectTimeZones
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import org.jetbrains.letsPlot.commons.intern.datetime.Instant as LPInstant
import org.jetbrains.letsPlot.commons.intern.datetime.TimeZone as LPTimeZone


class TimeZoneDetectionTest {

    @Test
    fun `detected timezone is recognized`() {
        // This test verifies that:
        // 1. `detectTimeZones` correctly extracts timezone IDs from `ZonedDateTime` and `OffsetDateTime` objects.
        // 2. After standardizing to a timestamp and reconstructing with the detected timezone,
        //    the resulting date-time components match the original objects.
        val zdtUtc = ZonedDateTime.of(2023, 1, 1, 10, 0, 0, 0, ZoneId.of("UTC"))
        val zdtMoscow = ZonedDateTime.of(2023, 1, 1, 10, 0, 0, 0, ZoneId.of("Europe/Moscow"))
        val zdtNewYork = ZonedDateTime.of(2023, 1, 1, 10, 0, 0, 0, ZoneId.of("America/New_York"))

        val odtUtc = OffsetDateTime.of(2024, 2, 2, 12, 30, 0, 0, ZoneOffset.UTC)
        val odtMinus5h = OffsetDateTime.of(2024, 2, 2, 12, 30, 0, 0, ZoneOffset.ofHours(-5))
        val odtPlus5h30m = OffsetDateTime.of(2024, 2, 2, 12, 30, 0, 0, ZoneOffset.ofHoursMinutes(5, 30))

        val testData = mapOf(
            "zdt_utc" to listOf(zdtUtc),
            "zdt_moscow" to listOf(zdtMoscow),
            "zdt_new_york" to listOf(zdtNewYork),
            "odt_utc" to listOf(odtUtc),
            "odt_minus_5h" to listOf(odtMinus5h),
            "odt_plus_5h_30m" to listOf(odtPlus5h30m)
        )

        val detectedTimeZones = detectTimeZones(testData)

        @Suppress("UNCHECKED_CAST")
        val standardizedData = Standardizing.standardizeValue(testData) as Map<String, List<Double>>

        fun check(original: ZonedDateTime, key: String) {
            val timestamp = standardizedData.getValue(key).single()
            val tzId = detectedTimeZones.getValue(key)
            val lpDateTime = LPInstant(timestamp.toLong()).toDateTime(LPTimeZone(tzId))

            assertEquals(original.year, lpDateTime.year)
            assertEquals(original.monthValue, lpDateTime.month.ordinal + 1)
            assertEquals(original.dayOfMonth, lpDateTime.day)
            assertEquals(original.hour, lpDateTime.time.hours)
            assertEquals(original.minute, lpDateTime.time.minutes)
            assertEquals(original.second, lpDateTime.time.seconds)
        }

        fun check(original: OffsetDateTime, key: String) {
            val timestamp = standardizedData.getValue(key).single()
            val tzId = detectedTimeZones.getValue(key)
            val lpDateTime = LPInstant(timestamp.toLong()).toDateTime(LPTimeZone(tzId))

            assertEquals(original.year, lpDateTime.year)
            assertEquals(original.monthValue, lpDateTime.month.ordinal + 1)
            assertEquals(original.dayOfMonth, lpDateTime.day)
            assertEquals(original.hour, lpDateTime.time.hours)
            assertEquals(original.minute, lpDateTime.time.minutes)
            assertEquals(original.second, lpDateTime.time.seconds)
        }

        check(zdtUtc, "zdt_utc")
        check(zdtMoscow, "zdt_moscow")
        check(zdtNewYork, "zdt_new_york")
        check(odtUtc, "odt_utc")
        check(odtMinus5h, "odt_minus_5h")
        check(odtPlus5h30m, "odt_plus_5h_30m")
    }
}
