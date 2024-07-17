/*
 * Copyright (c) 2021. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.core.spec.getList
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import kotlin.test.assertNotNull

class MappingAsDiscreteTest {

    @Test
    fun `check plot mapping`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) { x = asDiscrete("x") }

        val spec = p.toSpec()
        assertThat(spec).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                "kind" to "plot",
                "data" to mapOf("x" to listOf(1.0)),
                "data_meta" to mapOf(
                    SeriesAnnotation.TAG to listOf(
                        seriesAnnotation("x", SeriesAnnotation.Types.FLOATING)
                    ),
                    MappingAnnotation.TAG to listOf(
                        mappingAsDiscreteAnnotation(Aes.X, "x")
                    )
                ),
                "mapping" to mapOf("x" to "x"),
                "layers" to emptyList<Any>(),
                "scales" to emptyList<Any>(),
            ),
        )
    }

    @Test
    fun `check layer mapping`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) + geomPoint { x = asDiscrete("x") }

        val spec = p.toSpec()
        assertThat(spec).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                "kind" to "plot",
                "data" to mapOf("x" to listOf(1.0)),
                "data_meta" to mapOf(
                    SeriesAnnotation.TAG to listOf(
                        seriesAnnotation("x", SeriesAnnotation.Types.FLOATING)
                    )
                ),
                "mapping" to emptyMap<String, Any>(),
                "scales" to emptyList<Any>(),
                "layers" to listOf(
                    mapOf(
                        "geom" to "point",
                        "stat" to "identity",
                        "position" to "identity",
                        "mapping" to mapOf("x" to "x"),
                        DATA_META to mapOf(
                            MappingAnnotation.TAG to listOf(
                                mappingAsDiscreteAnnotation(Aes.X, "x")
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `check layer mapping with 2 vars`() {
        val data = mapOf(
            "x" to listOf(1.0),
            "y" to listOf(1.0)
        )
        val p = ggplot(data) + geomPoint { x = asDiscrete("x"); y = asDiscrete("y") }

        val spec = p.toSpec()
        assertThat(spec).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                "kind" to "plot",
                "data" to data,
                "data_meta" to mapOf(
                    SeriesAnnotation.TAG to listOf(
                        seriesAnnotation("x", SeriesAnnotation.Types.FLOATING),
                        seriesAnnotation("y", SeriesAnnotation.Types.FLOATING)
                    )
                ),
                "mapping" to emptyMap<String, Any>(),
                "scales" to emptyList<Any>(),
                "layers" to listOf(
                    mapOf(
                        "geom" to "point",
                        "stat" to "identity",
                        "position" to "identity",
                        "mapping" to mapOf("x" to "x", "y" to "y"),
                        DATA_META to mapOf(
                            MappingAnnotation.TAG to listOf(
                                mappingAsDiscreteAnnotation(Aes.X, "x"),
                                mappingAsDiscreteAnnotation(Aes.Y, "y")
                            ),
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `move levels from mapping_annotations to series_annotations`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) {
            x = "x"
            y = asDiscrete("x", levels = listOf(1.0))
        }

        val spec = p.toSpec()
        val dataMeta = spec[DATA_META] as? Map<*,*>
        assertNotNull(dataMeta)

        assertThat(dataMeta.getList(SeriesAnnotation.TAG)).contains(
            seriesAnnotation(column = "x", type = SeriesAnnotation.Types.FLOATING, factorLevels = listOf(1.0))
        )
        assertThat(dataMeta.getList(MappingAnnotation.TAG)).contains(
            mappingAsDiscreteAnnotation(aes = Aes.Y, label = "x")
        )
    }

}