/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.letsPlot.SeriesUtil.mappingAsDiscreteAnnotation
import org.jetbrains.letsPlot.SeriesUtil.seriesAnnotation
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.Option.Meta.DATA_META
import org.jetbrains.letsPlot.core.spec.Option.Meta.MappingAnnotation
import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation
import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation.Types
import org.jetbrains.letsPlot.core.spec.getList
import org.jetbrains.letsPlot.core.spec.getMap
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import java.time.Instant


class SeriesAnnotationTest {

    /**
    If levels are specified, the label should not be added to the mapping annotations.
    It's a bug, but Why not fix - if both levels and label are specified, the levels get ignored.
    */
    @Test
    fun `do not add label if factor level is specified`() {
        val data = mapOf(
            "X" to listOf(1, 0)
        )

        val p = ggplot(data) { x = asDiscrete("X", levels = listOf(0, 1), label = "The X") } + geomPoint()

        assertThat(p.toSpec().getMap(DATA_META))
            .doesNotContainKey(MappingAnnotation.TAG) // no MappingAnnotation - no label
        assertThat(p.toSpec().getList(DATA_META, SeriesAnnotation.TAG))
            .containsExactly(
                seriesAnnotation(column = "X", type = Types.INTEGER, factorLevels = listOf(0, 1))
            )
    }

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
    fun `single null in data`() {
        val data = mapOf(
            "single_null" to listOf(1, null, 3),
        )

        val p = ggplot(data) + geomLine { x = "v" }
        val spec = p.toSpec()

        assertThat(spec.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
            seriesAnnotation(column = "single_null", type = Types.INTEGER),
        )
    }

    @Test
    fun `all nulls in data`() {
        val data = mapOf(
            "all_nulls" to listOf(null, null, null),
        )

        val p = ggplot(data) + geomLine { x = "v" }
        val spec = p.toSpec()

        // Types.UNKNOWN should not be added to the SeriesAnnotation,
        // and empty SeriesAnnotation should not be added to DATA_META
        assertThat(spec.getList(DATA_META, SeriesAnnotation.TAG)).isNull()
    }

    @Test
    fun `empty data`() {
        val data = mapOf("v" to emptyList<Any>())
        val p = ggplot(data) + geomLine { x = "v" }
        assertThat(p.toSpec()).doesNotContainKey(DATA_META)
    }

    @Test
    fun `same variable with different ordering annotations asDiscrete(v1, order=X) and asDiscrete(v1, levels=X)`() {
        val data = mapOf(
            "v1" to listOf("a", "b", "c"),
        )

        val p = ggplot(data) {
            x = asDiscrete("v1", levels = listOf("b", "c", "a"))
            color = asDiscrete("v1", order = -1)
        } + geomPoint()

        assertThat(p.toSpec().getMap(DATA_META)).doesNotContainKey(MappingAnnotation.TAG)
        assertThat(p.toSpec().getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
            seriesAnnotation(column = "v1", type = Types.STRING, factorLevels = listOf("b", "c", "a"), order = -1)
        )
    }

    @Test
    fun asDiscreteRegression() {

        val data = mapOf(
            "v" to listOf("a", "b"),
            "bar" to listOf("b", "a")
        )

        (ggplot(data) { x = asDiscrete("v") } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).containsExactly(
                mappingAsDiscreteAnnotation(aes = Aes.X, label = "v")
            )
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING),
                seriesAnnotation(column = "bar", type = Types.STRING),
            )
        }

        (ggplot(data) { x = asDiscrete("v", order = 1) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).containsExactly(
                mappingAsDiscreteAnnotation(aes = Aes.X, label = "v", order = 1)
            )
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING),
                seriesAnnotation(column = "bar", type = Types.STRING)
            )
        }

        (ggplot(data) { x = asDiscrete("v", orderBy = "bar") } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).containsExactly(
                mappingAsDiscreteAnnotation(aes = Aes.X, label = "v", orderBy = "bar")
            )
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING),
                seriesAnnotation(column = "bar", type = Types.STRING)
            )
        }

        (ggplot(data) { x = asDiscrete("v", levels = listOf("b", "a")) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING, factorLevels = listOf("b", "a")),
                seriesAnnotation(column = "bar", type = Types.STRING)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(data) { x = asDiscrete("v", order = 1, levels = listOf("b", "a")) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING, factorLevels = listOf("b", "a"), order = 1),
                seriesAnnotation(column = "bar", type = Types.STRING)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(data) { x = asDiscrete("v", orderBy = "bar", levels = listOf("b", "a")) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING, factorLevels = listOf("b", "a")),
                seriesAnnotation(column = "bar", type = Types.STRING)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(data) { x = asDiscrete("v", order = 1, orderBy = "bar", levels = listOf("b", "a")) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.STRING, factorLevels = listOf("b", "a"), order = 1),
                seriesAnnotation(column = "bar", type = Types.STRING)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        val a = Instant.parse("2020-01-01T00:00:00Z")
        val b = Instant.parse("2020-01-02T00:00:00Z")

        (ggplot(mapOf("v" to listOf(a, b))) { x = asDiscrete("v") } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.DATE_TIME)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).containsExactly(
                mappingAsDiscreteAnnotation(aes = Aes.X, label = "v")
            )
        }

        (ggplot(mapOf("v" to listOf(a, b))) { x = asDiscrete("v", levels = listOf(b, a)) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.DATE_TIME, factorLevels = listOf(b, a))
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(mapOf("v" to listOf(a, b))) { x = asDiscrete("v", levels = listOf(b, a), order = 1) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.DATE_TIME, factorLevels = listOf(b, a), order = 1)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(mapOf("v" to listOf(a, b))) { x = asDiscrete("v", levels = listOf(b, a), orderBy = "v") } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.DATE_TIME, factorLevels = listOf(b, a))
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(mapOf("v" to listOf(a, b))) { x = asDiscrete("v", levels = listOf(b, a), order = 1, orderBy = "v") } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.DATE_TIME, factorLevels = listOf(b, a), order = 1)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).isNull()
        }

        (ggplot(mapOf("v" to listOf(a, b))) { x = asDiscrete("v", order = 1) } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
                seriesAnnotation(column = "v", type = Types.DATE_TIME)
            )
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).containsExactly(
                mappingAsDiscreteAnnotation(aes = Aes.X, label = "v", order = 1)
            )
        }
    }
}