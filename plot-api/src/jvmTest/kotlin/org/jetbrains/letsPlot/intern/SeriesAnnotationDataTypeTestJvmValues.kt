package org.jetbrains.letsPlot.intern

import java.time.*
import java.util.*

actual object SeriesAnnotationDataTypeTestJvmValues {
    actual fun getTestValues(): Map<String, Any> {
        return mapOf(
            "java.time.Instant" to Instant.parse("2023-01-01T12:30:45Z"),
            "java.time.ZonedDateTime" to ZonedDateTime.of(2023, 1, 1, 21, 30, 45, 0, ZoneId.of("Asia/Tokyo")),
            "java.time.LocalDate" to LocalDate.of(2023, 1, 1),
            "java.time.LocalTime" to LocalTime.of(12, 30, 45),
            "java.time.LocalDateTime" to LocalDateTime.of(2023, 1, 1, 12, 30, 45),
            "java.time.OffsetDateTime" to OffsetDateTime.of(2023, 1, 1, 12, 30, 45, 0, ZoneOffset.of("-05:00")),
            "java.time.OffsetDateTime-Z" to OffsetDateTime.of(2023, 1, 1, 12, 30, 45, 0, ZoneOffset.UTC),
            "java.util.Date" to Date.from(Instant.parse("2023-01-01T12:30:45Z")),
        )
    }

    actual fun getExpectedValues(): List<Map<String, String>> {
        return listOf(
            mapOf("column" to "java.time.Instant", "type" to "datetime"),
            mapOf("column" to "java.time.ZonedDateTime", "type" to "datetime", "time_zone" to "Asia/Tokyo"),
            mapOf("column" to "java.time.LocalDate", "type" to "date"),
            mapOf("column" to "java.time.LocalTime", "type" to "time"),
            mapOf("column" to "java.time.LocalDateTime", "type" to "datetime"),
            mapOf("column" to "java.time.OffsetDateTime", "type" to "datetime", "time_zone" to "UTC-05:00"),
            mapOf("column" to "java.time.OffsetDateTime-Z", "type" to "datetime", "time_zone" to "UTC"),
            mapOf("column" to "java.util.Date", "type" to "datetime"),
        )
    }
}