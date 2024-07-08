/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option.Meta.DATA_META
import org.jetbrains.letsPlot.core.spec.Option.Meta.MappingAnnotation
import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.MappingAnnotationSpecUtil.mappingAsDiscreteAnnotation
import org.jetbrains.letsPlot.MappingAnnotationSpecUtil.mappingDataMeta
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MappingAsDiscreteTest {

    @Test
    fun `check plot mapping`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) { x = asDiscrete("x") }

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "data" to mapOf("x" to listOf(1.0)),
                "mapping" to mapOf("x" to "x"),
                "layers" to emptyList<Any>(),
                "scales" to emptyList<Any>(),
                DATA_META to mappingDataMeta(
                    mappingAsDiscreteAnnotation(Aes.X, "x")
                )
            ),
            spec
        )
    }

    @Test
    fun `check layer mapping`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) + geomPoint { x = asDiscrete("x") }

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "data" to mapOf("x" to listOf(1.0)),
                "mapping" to emptyMap<String, Any>(),
                "scales" to emptyList<Any>(),
                "layers" to listOf(
                    mapOf(
                        "geom" to "point",
                        "stat" to "identity",
                        "position" to "identity",
                        "mapping" to mapOf("x" to "x"),
                        DATA_META to mappingDataMeta(
                            mappingAsDiscreteAnnotation(Aes.X, "x")
                        )
                    )
                )
            ),
            spec
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
        assertEquals(
            mapOf(
                "kind" to "plot",
                "data" to data,
                "mapping" to emptyMap<String, Any>(),
                "scales" to emptyList<Any>(),
                "layers" to listOf(
                    mapOf(
                        "geom" to "point",
                        "stat" to "identity",
                        "position" to "identity",
                        "mapping" to mapOf("x" to "x", "y" to "y"),
                        DATA_META to mappingDataMeta(
                            mappingAsDiscreteAnnotation(Aes.X, "x"),
                            mappingAsDiscreteAnnotation(Aes.Y, "y")
                        )
                    )
                )
            ),
            spec
        )
    }

    @Test
    fun `skip mapping_annotations when factor levels are set`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) {
            x = "x"
            y = asDiscrete("x", levels = listOf(1.0))
        }

        val spec = p.toSpec()
        val dataMeta = spec[DATA_META] as? Map<*,*>
        assertNotNull(dataMeta)
        assertFalse(MappingAnnotation.TAG in dataMeta)
        assertTrue(Option.Meta.SeriesAnnotation.TAG in dataMeta)
    }

}