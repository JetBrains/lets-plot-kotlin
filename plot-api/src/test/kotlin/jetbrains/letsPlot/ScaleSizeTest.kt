/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.scale.scale_size
import jetbrains.letsPlot.scale.scale_size_area
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScaleSizeTest {

    @Test
    fun `check scale size option`() {
        val p = ggplot() + scale_size(range = 5 to 10, limits = -10 to null)
        assertThat(p).features().length(1)

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<Any, Any>(),
                "layers" to emptyList<Any>(),
                "scales" to listOf(
                    mapOf(
                        "aesthetic" to "size",
                        "range" to listOf(5.0, 10.0),
                        "limits" to listOf(-10, null)
                    )
                )
            ),
            spec
        )
    }

    @Test
    fun `check scale size area option`() {
        val p = ggplot() + scale_size_area(maxSize = 30, limits = null to 20.5)
        assertThat(p).features().length(1)

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<Any, Any>(),
                "layers" to emptyList<Any>(),
                "scales" to listOf(
                    mapOf(
                        "aesthetic" to "size",
                        "max_size" to 30,
                        "limits" to listOf(null, 20.5),
                        "scale_mapper_kind" to "size_area"
                    )
                )
            ),
            spec
        )
    }
}