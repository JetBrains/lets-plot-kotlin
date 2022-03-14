/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.standardizing

import jetbrains.datalore.base.values.Color
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class StandardizingTest {
    @Test
    fun test() {
        val result = SeriesStandardizing.toList(INPUT)
        assertEquals(EXPECT, result)
    }

    companion object {
        private val ZONED_DATE_TIME: ZonedDateTime = ZonedDateTime.of(
            2020,
            6,
            10,
            13,
            58,
            0,
            0,
            ZoneId.of("EST", ZoneId.SHORT_IDS)
        )

        private val INSTANT: Instant = ZONED_DATE_TIME.toInstant()
        private val DATE: Date = Date(INSTANT.toEpochMilli())


        val INPUT: List<Any?> = listOf(
            null,
            "Str",
            0, 0L, 0.0, 1E3,
            Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            State.Idle,
            Color.WHITE,
            java.awt.Color.WHITE,
            ZONED_DATE_TIME,
            INSTANT,
            DATE,
        )

        val EXPECT: List<Any?> = listOf(
            null,
            "Str",
            0.0, 0.0, 0.0, 1000.0,
            null, null, null,
            null, null, null,
            "Idle",
            "#ffffff",
            "#ffffff",
            1591815480000,
            1591815480000,
            1591815480000,
        )
    }
}

private enum class State { Idle, Working }