/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Meta.DATA_META
import jetbrains.datalore.plot.config.Option.Meta.MappingAnnotation
import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Test

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
                DATA_META to mappingDataMeta(asDiscreteAnnotation(Aes.X, "x"))
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
                        DATA_META to mappingDataMeta(asDiscreteAnnotation(Aes.X, "x"))
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
                            asDiscreteAnnotation(Aes.X, "x"),
                            asDiscreteAnnotation(Aes.Y, "y")
                        )
                    )
                )
            ),
            spec
        )
    }

    companion object {
        private fun mappingDataMeta(vararg mappingAnnotations: Map<String, *>): Map<String, Any> {
            return mapOf(
                MappingAnnotation.TAG to mappingAnnotations.asList()
            )
        }

        private fun asDiscreteAnnotation(aes: Aes<*>, label: String): Map<String, Any> {
            return mapOf(
                MappingAnnotation.AES to aes.name,
                MappingAnnotation.ANNOTATION to MappingAnnotation.AS_DISCRETE,
                MappingAnnotation.PARAMETERS to mapOf(
                    MappingAnnotation.LABEL to label,
                    MappingAnnotation.ORDER_BY to null,
                    MappingAnnotation.ORDER to null
                )
            )
        }
    }
}