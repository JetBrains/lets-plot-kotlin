/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Meta.DATA_META
import jetbrains.datalore.plot.config.Option.Meta.MappingAnnotation
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MappingAsDiscreteTest {

    @Test
    fun `check plot mapping`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) { x = as_discrete("x") }

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "data" to mapOf("x" to listOf(1.0)),
                "mapping" to mapOf("x" to "x"),
                "layers" to emptyList<Any>(),
                "scales" to emptyList<Any>(),
                DATA_META to EXP_DATA_META
            ),
            spec
        )
    }

    @Test
    fun `check layer mapping`() {
        val data = mapOf("x" to listOf(1.0))
        val p = ggplot(data) + geom_point { x = as_discrete("x") }

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
                        DATA_META to EXP_DATA_META
                    )
                )
            ),
            spec
        )
    }

    companion object {
        private val EXP_DATA_META = mapOf(
            MappingAnnotation.TAG to listOf(
                mapOf(
                    MappingAnnotation.AES to Aes.X.name,
                    MappingAnnotation.ANNOTATION to MappingAnnotation.AS_DISCRETE,
                    MappingAnnotation.PARAMETERS to mapOf(
                        MappingAnnotation.LABEL to "x"
                    )
                )
            )
        )

    }
}