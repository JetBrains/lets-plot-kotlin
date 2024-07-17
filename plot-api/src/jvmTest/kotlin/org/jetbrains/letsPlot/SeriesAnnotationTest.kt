/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.letsPlot.MappingAnnotationSpecUtil.mappingAsDiscreteAnnotation
import org.jetbrains.letsPlot.SeriesAnnotationUtil.seriesAnnotation
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.Option.Meta.DATA_META
import org.jetbrains.letsPlot.core.spec.Option.Meta.MappingAnnotation
import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation
import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation.Types
import org.jetbrains.letsPlot.core.spec.getList
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset


class SeriesAnnotationTest {

    @Test
    fun dtypes() {
        val data = mapOf(
            "byte-column" to listOf(1.toByte(), 2.toByte(), 3.toByte()),
            "short-column" to listOf(1.toShort(), 2.toShort(), 3.toShort()),
            "int-column" to listOf(1, 2, 3),
            "long-column" to listOf(1L, 2L, 3L),
            "double-column" to listOf(1.0, 2.0, 3.0),
            "float-column" to listOf(1.0f, 2.0f, 3.0f),
            "string-column" to listOf("a", "b", "c"),
            "boolean-column" to listOf(true, false, true),
            "java-instant-column" to listOf(Instant.parse("2021-01-01T00:00:00Z")),
            "kotlin-instant-column" to listOf(kotlinx.datetime.Instant.parse("2021-01-01T00:00:00Z"))
        )

        val p = ggplot(data) + geomPoint()

        assertThat(p.toSpec()[DATA_META]).isEqualTo(
            mapOf(
                SeriesAnnotation.TAG to listOf(
                    seriesAnnotation(column = "byte-column", type = Types.INTEGER),
                    seriesAnnotation(column = "short-column", type = Types.INTEGER),
                    seriesAnnotation(column = "int-column", type = Types.INTEGER),
                    seriesAnnotation(column = "long-column", type = Types.INTEGER),
                    seriesAnnotation(column = "double-column", type = Types.FLOATING),
                    seriesAnnotation(column = "float-column", type = Types.FLOATING),
                    seriesAnnotation(column = "string-column", type = Types.STRING),
                    seriesAnnotation(column = "boolean-column", type = Types.BOOLEAN),
                    seriesAnnotation(column = "java-instant-column", type = Types.DATE_TIME),
                    seriesAnnotation(column = "kotlin-instant-column", type = Types.DATE_TIME)
                )
            )
        )
    }

    @Test
    fun `no date-time data`() {
        val instant = LocalDateTime.of(2003, Month.FEBRUARY, 1, 0, 0, 0).toInstant(ZoneOffset.UTC)
        val data = mapOf(
            "v" to listOf(instant.epochSecond) // it's List of Long
        )
        val p = ggplot(data) + geomLine { x = "v" }
        assertThat(p.toSpec()[DATA_META]).isEqualTo(
            mapOf(
                SeriesAnnotation.TAG to listOf(
                    seriesAnnotation(column = "v", type = Types.INTEGER)
                )
            )
        )
    }

    @Test
    fun `empty data`() {
        val data = mapOf("v" to emptyList<Any>())
        val p = ggplot(data) + geomLine { x = "v" }
        assertThat(p.toSpec()).doesNotContainKey(DATA_META)
    }

    @Test
    fun `few series`() {
        val dtSeries = listOf(LocalDateTime.of(2003, Month.FEBRUARY, 1, 0, 0, 0).toInstant(ZoneOffset.UTC))
        val data = mapOf(
            "v1" to dtSeries,
            "v2" to dtSeries,
            "v3" to listOf(1.0)
        )
        val p = ggplot(data) + geomLine { x = "v1"; y = "v2"; color = "v3" }

        assertThat(p.toSpec()[DATA_META]).isEqualTo(
            mapOf(
                SeriesAnnotation.TAG to listOf(
                    seriesAnnotation(column = "v1", type = Types.DATE_TIME),
                    seriesAnnotation(column = "v2", type = Types.DATE_TIME),
                    seriesAnnotation(column = "v3", type = Types.FLOATING)
                )
            )
        )
    }


    @Test
    fun `factor levels series annotations`() {
        val data = mapOf(
            "v1" to listOf("a", "b", "c"),
            "v2" to listOf(1.0, 2.0, 3.0)
        )

        val p = ggplot(data) {
            x = asDiscrete("v1", levels = listOf("b", "c", "a"))
            y = "v2"
            color = asDiscrete("v1", order = -1)
            fill = asDiscrete("v2", levels = listOf(3.0, 1.0, 2.0))
        } + geomPoint()

        assertThat(p.toSpec().getList(DATA_META, MappingAnnotation.TAG)).containsExactlyInAnyOrder(
            mappingAsDiscreteAnnotation(aes = Aes.X, label = "v1"),
            mappingAsDiscreteAnnotation(aes = Aes.COLOR, label = "v1"),
            mappingAsDiscreteAnnotation(aes = Aes.FILL, label = "v2"),
        )

        assertThat(p.toSpec().getList(DATA_META, SeriesAnnotation.TAG)).containsExactlyInAnyOrder(
            seriesAnnotation(
                column = "v1",
                type = Types.STRING,
                factorLevels = listOf("b", "c", "a"),
                order = -1
            ),
            seriesAnnotation(
                column = "v2",
                type = Types.FLOATING,
                factorLevels = listOf(3.0, 1.0, 2.0)
            )
        )
    }

}