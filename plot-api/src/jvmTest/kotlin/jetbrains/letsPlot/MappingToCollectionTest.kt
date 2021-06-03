/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MappingToCollectionTest {

    @Test
    fun `check plot mapping`() {
        val p = ggplot { x = intArrayOf(1, 2, 3); y = (1..3) }

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to mapOf(
                    "x" to listOf(1.0, 2.0, 3.0),
                    "y" to listOf(1.0, 2.0, 3.0)
                ),
                "layers" to emptyList<Any>(),
                "scales" to emptyList<Any>()
            ),
            spec
        )
    }

    @Test
    fun `check layer mapping`() {
        val p = ggplot() + geomPoint { x = intArrayOf(1, 2, 3); y = (1..3) }

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<String, Any>(),
                "scales" to emptyList<Any>(),
                "layers" to listOf(
                    mapOf(
                        "geom" to "point",
                        "stat" to "identity",
                        "position" to "identity",
                        "mapping" to mapOf(
                            "x" to listOf(1.0, 2.0, 3.0),
                            "y" to listOf(1.0, 2.0, 3.0)
                        )
                    )
                )
            ),
            spec
        )
    }
}