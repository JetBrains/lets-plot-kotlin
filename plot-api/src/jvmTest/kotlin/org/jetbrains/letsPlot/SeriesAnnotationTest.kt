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
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import java.time.Instant


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

        assertThat(p.toSpec().getList(DATA_META, MappingAnnotation.TAG)).containsExactlyInAnyOrder(
            mappingAsDiscreteAnnotation(aes = Aes.X, label = "v1"),
            mappingAsDiscreteAnnotation(aes = Aes.COLOR, label = "v1"),
        )

        assertThat(p.toSpec().getList(DATA_META, SeriesAnnotation.TAG)).containsExactly(
            seriesAnnotation(column = "v1", type = Types.STRING, factorLevels = listOf("b", "c", "a"), order = -1)
        )
    }

    @Test
    fun asDiscrete() {
        /*
    # no order
    p = ggplot(mapping=aes(x=as_discrete('foo'))) + geom_point()
    assert p.as_dict()['data_meta']['mapping_annotations'] == [
        {'aes': 'x', 'annotation': 'as_discrete', 'parameters': {'label': 'foo', 'order': None, 'order_by': None}}
    ]
    assert 'series_annotations' not in p.as_dict()['data_meta']

    # with order
    p = ggplot(mapping=aes(x=as_discrete('foo', order=1))) + geom_point()
    assert p.as_dict()['data_meta']['mapping_annotations'] == [
        {'aes': 'x', 'annotation': 'as_discrete', 'parameters': {'label': 'foo', 'order': 1, 'order_by': None}}
    ]
    assert 'series_annotations' not in p.as_dict()['data_meta']

    # with order_by
    p = ggplot(mapping=aes(x=as_discrete('foo', order_by='bar'))) + geom_point()
    assert p.as_dict()['data_meta']['mapping_annotations'] == [
        {'aes': 'x', 'annotation': 'as_discrete', 'parameters': {'label': 'foo', 'order': None, 'order_by': 'bar'}}
    ]
    assert 'series_annotations' not in p.as_dict()['data_meta']

    # with levels
    p = ggplot(mapping=aes(x=as_discrete('foo', levels=['a', 'b']))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'foo', 'factor_levels': ['a', 'b'], 'order': None}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with order and levels
    p = ggplot(mapping=aes(x=as_discrete('foo', order=1, levels=['a', 'b']))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'foo', 'factor_levels': ['a', 'b'], 'order': 1}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with order_by and levels
    p = ggplot(mapping=aes(x=as_discrete('foo', order_by='bar', levels=['a', 'b']))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'foo', 'factor_levels': ['a', 'b'], 'order': None}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with order, order_by and levels
    p = ggplot(mapping=aes(x=as_discrete('foo', order=1, order_by='bar', levels=['a', 'b']))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'foo', 'factor_levels': ['a', 'b'], 'order': 1}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    from datetime import datetime
    a = datetime(2020, 1, 1)
    b = datetime(2020, 1, 2)

    # with datetime
    p = ggplot({'v': [a, b]}, aes(x=as_discrete('v'))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'v', 'type': 'datetime'}
    ]
    assert p.as_dict()['data_meta']['mapping_annotations'] == [
        {'aes': 'x', 'annotation': 'as_discrete', 'parameters': {'label': 'v', 'order': None, 'order_by': None}}
    ]

    # with datetime and levels - WRONG, should be one annotation with type and factor_levels
    p = ggplot({'v': [a, b]}, aes(x=as_discrete('v', levels=[b, a]))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'v', 'factor_levels': [b, a], 'order': None},
        {'column': 'v', 'type': 'datetime'}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with datetime, levels and order - WRONG, should be one annotation with type, factor_levels and order
    p = ggplot({'v': [a, b]}, aes(x=as_discrete('v', levels=[b, a], order=1))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'v', 'factor_levels': [b, a], 'order': 1},
        {'column': 'v', 'type': 'datetime'}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with datetime, levels and order_by - WRONG, should be one annotation with type, factor_levels and order_by
    p = ggplot({'v': [a, b]}, aes(x=as_discrete('v', levels=[b, a], order_by='v'))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'v', 'factor_levels': [b, a], 'order': None},
        {'column': 'v', 'type': 'datetime'}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with datetime, levels, order and order_by - WRONG, should be one annotation with type, factor_levels, order and order_by
    p = ggplot({'v': [a, b]}, aes(x=as_discrete('v', levels=[b, a], order=1, order_by='v'))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'v', 'factor_levels': [b, a], 'order': 1},
        {'column': 'v', 'type': 'datetime'}
    ]
    assert 'mapping_annotations' not in p.as_dict()['data_meta']

    # with datetime, order - WRONG, should be one annotation with type and order
    p = ggplot({'v': [a, b]}, aes(x=as_discrete('v', order=1))) + geom_point()
    assert p.as_dict()['data_meta']['series_annotations'] == [
        {'column': 'v', 'type': 'datetime'}
    ]
    assert p.as_dict()['data_meta']['mapping_annotations'] == [
        {'aes': 'x', 'annotation': 'as_discrete', 'parameters': {'label': 'v', 'order': 1, 'order_by': None}}
    ]
*/

        val data = mapOf(
            "v" to listOf("a", "b"),
        )

        (ggplot(data) { x = asDiscrete("v") } + geomPoint()).toSpec().let {
            assertThat(it.getList(DATA_META, MappingAnnotation.TAG)).containsExactly(
                mappingAsDiscreteAnnotation(aes = Aes.X, label = "v")
            )
            assertThat(it.getList(DATA_META, SeriesAnnotation.TAG)).isNull()
        }
    }
}