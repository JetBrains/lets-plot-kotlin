/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.commons.testing.assertContentEquals
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.letsPlot
import kotlin.test.Test

import kotlinx.datetime.Instant as KInstant
import kotlinx.datetime.LocalDate as KLocalDate
import kotlinx.datetime.LocalDateTime as KLocalDateTime
import kotlinx.datetime.LocalTime as KLocalTime


@Suppress("ClassName")
class SeriesAnnotation_TypeAndTZTest {

    @Test
    fun infer_type_and_timezone() {
        val testData = (

                mapOf(
                    "Byte" to 1.toByte(),
                    "Short" to 2.toShort(),
                    "Int" to 2,
                    "Long" to 4L,
                    "Float" to 5.1f,
                    "Double" to 6.2,
                    "Boolean" to true,
                    "String" to "text",

                    // Unknown type: won't be added to series annotations.
                    "Null" to null,
                    "Object" to State.Idle,

                    // kotlinx.datetime API
                    "kotlinx.Instant" to KInstant.fromEpochMilliseconds(1672576245000L), // 2023-01-01T12:30:45Z
                    "kotlinx.LocalDate" to KLocalDate(2023, 1, 1), // 2023-01-01
                    "kotlinx.LocalTime" to KLocalTime(12, 30, 45), // 12:30:45
                    "kotlinx.LocalDateTime" to KLocalDateTime(2023, 1, 1, 12, 30, 45), // 2023-01-01T12:30:45
                ) + SeriesAnnotationDataTypeTestJvmValues.getTestValues()

                ).mapValues { (_, value) -> listOf(value) }


        val expectedSeriesAnnotations = listOf(

            mapOf("column" to "Byte", "type" to "int"),
            mapOf("column" to "Short", "type" to "int"),
            mapOf("column" to "Int", "type" to "int"),
            mapOf("column" to "Long", "type" to "int"),
            mapOf("column" to "Float", "type" to "float"),
            mapOf("column" to "Double", "type" to "float"),
            mapOf("column" to "Boolean", "type" to "bool"),
            mapOf("column" to "String", "type" to "str"),

            // Unknown type: won't be added to series annotations:
            // - Null
            // - Object

            // None of kotlinx.datetime objects store time zone information.
            mapOf("column" to "kotlinx.Instant", "type" to "datetime"),
            mapOf("column" to "kotlinx.LocalDate", "type" to "date"),
            mapOf("column" to "kotlinx.LocalTime", "type" to "time"),
            mapOf("column" to "kotlinx.LocalDateTime", "type" to "datetime"),

            ) + SeriesAnnotationDataTypeTestJvmValues.getExpectedValues()


        val plot = letsPlot(testData) + geomPoint()
        val seriesAnnotations = (plot.toSpec()["data_meta"] as? Map<*, *>)?.get("series_annotations")
        assertContentEquals(expectedSeriesAnnotations, seriesAnnotations as List<*>)
    }
}

@Suppress("unused")
private enum class State { Idle, Working }