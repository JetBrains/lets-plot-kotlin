/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.letsPlot.commons.values.Color
import kotlin.test.*
import kotlinx.datetime.Instant as KInstant
import kotlinx.datetime.LocalDate as KLocalDate
import kotlinx.datetime.LocalDateTime as KLocalDateTime
import kotlinx.datetime.LocalTime as KLocalTime
import org.jetbrains.letsPlot.commons.intern.datetime.Date as LPDate
import org.jetbrains.letsPlot.commons.intern.datetime.DateTime as LPDateTime
import org.jetbrains.letsPlot.commons.intern.datetime.Month as LPMonth
import org.jetbrains.letsPlot.commons.intern.datetime.Time as LPTime
import org.jetbrains.letsPlot.commons.intern.datetime.TimeZone as LPTimeZone


class StandardizingTest {

    @Test
    fun numeric_values_standardized_to_a_Double() {
        val numericValues = listOf(
            1.toByte(),         // Byte
            2.toShort(),        // Short
            2,                  // Int
            4L,                 // Long
            5.1f,               // Float
            6.2                 // Double
        )
        val expected = numericValues.map { it.toDouble() }

        val result = SeriesStandardizing.toList(numericValues)
        assertContentEquals(expected, result)
    }

    @Test
    fun temporal_values_standardized_to_a_Double() {
        // Expected timestamp for 2023-01-01T12:30:45Z
        val expectedTimestamp = 1672576245000L

        val expectedLocalDateTimestamp = KLocalDateTime(2023, 1, 1, 0, 0, 0, 0)
            .toInstant(TimeZone.UTC)
            .toEpochMilliseconds()

        val expectedLocalTimeTimestamp = KLocalDateTime(1970, 1, 1, 12, 30, 45, 0)
            .toInstant(TimeZone.UTC)
            .toEpochMilliseconds()

        // Kotlinx.datetime API
        val kInstant = KInstant.fromEpochMilliseconds(expectedTimestamp)
        val kLocalDate = KLocalDate(2023, 1, 1)
        val kLocalTime = KLocalTime(12, 30, 45)
        val kLocalDateTime = KLocalDateTime(2023, 1, 1, 12, 30, 45)

        val values = listOf<Any>(
            kInstant,
            kLocalDate,
            kLocalTime,
            kLocalDateTime,
        )

        val expectedValues = values.map {
            when (it) {
                is KLocalDate -> expectedLocalDateTimestamp
                is KLocalTime -> expectedLocalTimeTimestamp
                is KLocalDateTime -> expectedTimestamp      // Same as ZonedDateTime because here we use UTC
                else -> expectedTimestamp
            }.toDouble()
        } + StandardizingTestJvmValues.getExpectedValues()


        val inputValues = values + StandardizingTestJvmValues.getTestValues()
        val standardizedValues = SeriesStandardizing.toList(inputValues)
        inputValues.zip(expectedValues).zip(standardizedValues) { (a, b), c ->
            Triple(a, b, c)
        }
            .forEach { (input, expected, result) ->
                // Expect all results to be Double
                assertTrue(
                    result is Double,
                    "Input: $input, ${input::class}, result expected Double but was: ${result!!::class}"
                )

                assertEquals(expected, result, "Input: $input, ${input::class}")
            }
    }

    @Test
    fun lp_internal_datetime_values_are_not_supported() {
        val date = LPDate(1, LPMonth.JANUARY, 2023)
        val time = LPTime(12, 30, 45)
        val dateTime = LPDateTime(date, time)
        val instant = dateTime.toInstant(LPTimeZone("UTC"))

        assertFailsWith<IllegalArgumentException> {
            Standardizing.standardizeValue(instant)
        }
        assertFailsWith<IllegalArgumentException> {
            Standardizing.standardizeValue(date)
        }
        assertFailsWith<IllegalArgumentException> {
            Standardizing.standardizeValue(time)
        }
        assertFailsWith<IllegalArgumentException> {
            Standardizing.standardizeValue(dateTime)
        }
    }


    @Test
    fun a_collection_of_mixed_values() {
        // LocalDateTime in EST (UTC-5)
        val localDateTime = KLocalDateTime(2020, 6, 10, 13, 58)
//        val timeZone = TimeZone.of("UTC-05:00")
        val timeZone = TimeZone.of("UTC")
        val instant = localDateTime.toInstant(timeZone)


        val values = listOf(
            null,
            "Str",
            0, 0L, 0.0, 1E3,
            Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            State.Idle,
            Color.WHITE,
            instant,
            localDateTime,
        )

        val expected = listOf(
            null,
            "Str",
            0.0, 0.0, 0.0, 1000.0,
            null, null, null,
            null, null, null,
            "Idle",
            "#ffffff",
            1591797480000L.toDouble(),
            1591797480000L.toDouble(),
        )

        val result = SeriesStandardizing.toList(values)
        assertContentEquals(expected, result)
    }
}

@Suppress("unused")
private enum class State { Idle, Working }