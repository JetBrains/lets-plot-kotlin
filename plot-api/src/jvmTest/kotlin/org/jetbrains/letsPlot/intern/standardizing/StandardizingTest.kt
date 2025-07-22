/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import org.jetbrains.letsPlot.commons.values.Color
import java.time.*
import java.util.*
import kotlin.test.*
import kotlinx.datetime.Instant as KInstant
import kotlinx.datetime.LocalDate as KLocalDate
import kotlinx.datetime.LocalDateTime as KLocalDateTime
import kotlinx.datetime.LocalTime as KLocalTime
import org.jetbrains.letsPlot.commons.intern.datetime.Date as LPDate
import org.jetbrains.letsPlot.commons.intern.datetime.DateTime as LPDateTime
import org.jetbrains.letsPlot.commons.intern.datetime.Month as LPMonth
import org.jetbrains.letsPlot.commons.intern.datetime.Time as LPTime
import org.jetbrains.letsPlot.commons.intern.datetime.TimeZone as LPTZ


class StandardizingTest {

    @Test
    fun `Numeric values standardized to a Double`() {
        val numericValues = listOf(
            1.toByte(),         // Byte
            2.toShort(),        // Short
            2,                  // Int
            4L,                 // Long
            5.1f,                // Float
            6.2                  // Double
        )
        val expected = numericValues.map { it.toDouble() }

        val result = SeriesStandardizing.toList(numericValues)
        assertContentEquals(expected, result)
    }

    @Test
    fun `Temporal values standardized to a Double`() {
        // Expected timestamp for 2023-01-01T12:30:45Z
        val expectedTimestamp = 1672576245000L

        val expectedLocalDateTimestamp = ZonedDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))
            .toInstant().toEpochMilli()

        val expectedLocalTimeTimestamp = ZonedDateTime.of(1970, 1, 1, 12, 30, 45, 0, ZoneId.of("UTC"))
            .toInstant().toEpochMilli()

        // Java time API
        val zonedDateTime = ZonedDateTime.of(2023, 1, 1, 12, 30, 45, 0, ZoneId.of("UTC"))
        val instant = zonedDateTime.toInstant()
        val localDate = zonedDateTime.toLocalDate()
        val localTime = zonedDateTime.toLocalTime()
        val localDateTime = zonedDateTime.toLocalDateTime()
        val date = Date.from(instant)

        // Kotlinx.datetime API
        val kInstant = KInstant.fromEpochMilliseconds(expectedTimestamp)
        val kLocalDate = KLocalDate(2023, 1, 1)
        val kLocalTime = KLocalTime(12, 30, 45)
        val kLocalDateTime = KLocalDateTime(2023, 1, 1, 12, 30, 45)

        val timestampValues = listOf(
            zonedDateTime,
            instant,
            localDate,
            localTime,
            localDateTime,
            date,

            kInstant,
            kLocalDate,
            kLocalTime,
            kLocalDateTime,
        )

        val timestampValuesStandardized = SeriesStandardizing.toList(timestampValues)
        timestampValues.zip(timestampValuesStandardized).forEach { (input, result) ->
            assertTrue(
                result is Double,
                "Input: $input, ${input.javaClass.name}, result expected Double but was: ${result?.javaClass?.name}"
            )

            val expected = when (input) {
                is LocalDate,
                is KLocalDate -> expectedLocalDateTimestamp.toDouble()

                is LocalTime,
                is KLocalTime -> expectedLocalTimeTimestamp.toDouble()

                is LocalDateTime,
                is KLocalDateTime -> expectedTimestamp.toDouble() // Same as ZonedDateTime because here we use UTC

                else -> expectedTimestamp.toDouble()
            }

            assertEquals(expected, result, "Input: $input, ${input.javaClass.name}")
        }
    }

    @Test
    fun `LP internal datetime values are not supported`() {
        val date = LPDate(1, LPMonth.JANUARY, 2023)
        val time = LPTime(12, 30, 45)
        val dateTime = LPDateTime(date, time)
        val instant = dateTime.toInstant(LPTZ("UTC"))

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
    fun `Collection of mixed values`() {
        val dateTime = ZonedDateTime.of(
            2020,
            6,
            10,
            13,
            58,
            0,
            0,
            ZoneId.of("EST", ZoneId.SHORT_IDS)
        )


        val values = listOf(
            null,
            "Str",
            0, 0L, 0.0, 1E3,
            Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            State.Idle,
            Color.WHITE,
            java.awt.Color.WHITE,
            dateTime,
            dateTime.toInstant(),
            Date(dateTime.toInstant().toEpochMilli()),
        )

        val expected = listOf(
            null,
            "Str",
            0.0, 0.0, 0.0, 1000.0,
            null, null, null,
            null, null, null,
            "Idle",
            "#ffffff",
            "#ffffff",
            1.59181548E12,
            1.59181548E12,
            1.59181548E12
        )

        val result = SeriesStandardizing.toList(values)
        assertEquals(expected, result)
    }
}

private enum class State { Idle, Working }