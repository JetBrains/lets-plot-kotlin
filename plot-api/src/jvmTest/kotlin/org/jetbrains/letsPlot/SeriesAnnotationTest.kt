/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.commons.intern.datetime.Date
import org.jetbrains.letsPlot.commons.intern.datetime.DateTime
import org.jetbrains.letsPlot.commons.intern.datetime.Month
import org.jetbrains.letsPlot.commons.intern.datetime.tz.TimeZone
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class SeriesAnnotationTest {

    private fun checkDataSeries(
        values: Any,
        isDateTime: Boolean
    ) {
        val data = mapOf("v" to values)
        val p = ggplot(data) + geomLine { x = "v" }
        val spec = p.toSpec()
        if (isDateTime) {
            assertSeriesAnnotations(
                spec,
                expected = seriesAnnotations(dateTimeAnnotation("v"))
            )
        } else {
            assertNoSeriesAnnotation(spec)
        }
    }

    @Test
    fun `date-time series - different types of series`() {
        val instant = TimeZone.UTC.toInstant(DateTime(Date(1, Month.FEBRUARY, 2003)))

        run {
            checkDataSeries(values = (listOf(instant)), isDateTime = true)
        }
        run {
            checkDataSeries(values = (arrayOf(instant)), isDateTime = true)
        }
        run {
            checkDataSeries(values = (sequenceOf(instant)), isDateTime = true)
        }
        run {
            checkDataSeries(values = (listOf(instant).asIterable()), isDateTime = true)
        }
    }

    @Test
    fun `date-time series - java_time_Instant`() {
        val instant = Instant.parse("2021-01-01T00:00:00Z")
        checkDataSeries(values = listOf(instant), isDateTime = true)
    }

    @Test
    fun `date-time series - kotlinx_datetime_Instant`() {
        val instant = kotlinx.datetime.Instant.parse("2021-01-01T00:00:00Z")
        checkDataSeries(values = listOf(instant), isDateTime = true)
    }

    @Test
    fun `no date-time data`() {
        val instant = TimeZone.UTC.toInstant(DateTime(Date(1, Month.FEBRUARY, 2003)))
        checkDataSeries(
            values = listOf(instant.timeSinceEpoch), // it's List of Long
            isDateTime = false
        )
    }

    @Test
    fun `empty data`() {
        checkDataSeries(
            values = emptyList<Any>(),
            isDateTime = false
        )
    }

    @Test
    fun `few series`() {
        val dtSeries = listOf(TimeZone.UTC.toInstant(DateTime(Date(1, Month.FEBRUARY, 2003))))
        val data = mapOf("v1" to dtSeries, "v2" to dtSeries, "v3" to listOf(1.0))
        val p = ggplot(data) + geomLine { x = "v1"; y = "v2"; color = "v3" }

        assertSeriesAnnotations(
            p.toSpec(),
            expected = seriesAnnotations(dateTimeAnnotation("v1"), dateTimeAnnotation("v2")),
        )
    }


    @Test
    fun `factor levels series annotations`() {
        val data = mapOf("v1" to listOf("a", "b", "c"), "v2" to listOf(1.0, 2.0, 3.0))
        val p = ggplot(data) {
            x = asDiscrete("v1", levels = listOf("b", "c", "a"))
            y = "v2"
            color = asDiscrete("v1", order = -1)
            fill = asDiscrete("v2", levels = listOf(3.0, 1.0, 2.0))
        } +  geomPoint()

        assertSeriesAnnotations(
            p.toSpec(),
            expected = seriesAnnotations(
                factorLevelsAnnotation("v1",  listOf("b", "c", "a"), order = -1),
                factorLevelsAnnotation("v2",  listOf(3.0, 1.0, 2.0), order = null),
            )
        )
    }

    companion object {
        // { 'series_annotations': [ {'column': 'name', 'type': 'datetime'}, { ... } ] }

        private fun seriesAnnotations(vararg seriesAnnotations: Map<String, *>): Map<String, Any> {
            return mapOf(
                Option.Meta.SeriesAnnotation.TAG to seriesAnnotations.asList()
            )
        }

        private fun dateTimeAnnotation(varName: String): Map<String, Any> {
            return mapOf(
                Option.Meta.SeriesAnnotation.COLUMN to varName,
                Option.Meta.SeriesAnnotation.TYPE to Option.Meta.SeriesAnnotation.DateTime.DATE_TIME
            )
        }

        // { 'series_annotations': [ {'column': 'name', 'factor_levels': levels, 'order': order}, { ... } ] }
        private fun factorLevelsAnnotation(varName: String, levels: List<Any>, order: Int?): Map<String, Any?> {
            return mapOf(
                Option.Meta.SeriesAnnotation.COLUMN to varName,
                Option.Meta.SeriesAnnotation.FACTOR_LEVELS to levels,
                Option.Meta.SeriesAnnotation.ORDER to order
            )
        }

        private fun assertSeriesAnnotations(
            spec: MutableMap<String, Any>,
            expected: Map<String, Any>
        ) {
            assertTrue(Option.Meta.DATA_META in spec, message = "Series should be detected as date/time")
            assertEquals(
                expected,
                spec[Option.Meta.DATA_META]
            )
        }

        private fun assertNoSeriesAnnotation(spec: MutableMap<String, Any>) {
            assertTrue(Option.Meta.DATA_META !in spec, message = "Series shouldn't be detected as date/time")
        }

    }
}