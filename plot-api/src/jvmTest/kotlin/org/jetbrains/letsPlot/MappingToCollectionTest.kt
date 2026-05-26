/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
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

    @Test
    fun `unknown values in plot data are stringified`() {
        // Issue #184
        val p = ggplot(mapOf("x" to listOf(A(1), A(2)), "y" to listOf(1, 2))) +
                geomPoint { x = "x"; y = "y" }

        val spec = p.toSpec()

        @Suppress("UNCHECKED_CAST")
        val data = spec["data"] as Map<String, Any>
        assertEquals(listOf("A(a=1)", "A(a=2)"), data["x"])
        assertEquals(listOf(1.0, 2.0), data["y"])
    }

    @Test
    fun `unknown values in layer collection mappings are stringified`() {
        // Issue #184
        val p = ggplot() + geomPoint {
            x = listOf(A(1), A(2))
            y = listOf(1, 2)
            color = listOf(A(1), A(2))
        }

        val spec = p.toSpec()

        @Suppress("UNCHECKED_CAST")
        val layerMapping = ((spec["layers"] as List<*>).first() as Map<String, Any>)["mapping"] as Map<String, Any>
        assertEquals(listOf("A(a=1)", "A(a=2)"), layerMapping["x"])
        assertEquals(listOf(1.0, 2.0), layerMapping["y"])
        assertEquals(listOf("A(a=1)", "A(a=2)"), layerMapping["color"])
    }
}

private data class A(val a: Int)