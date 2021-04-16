/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.sampling.samplingNone
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SamplingNoneTest {

    @Test
    fun noneSampling() {
        val p = ggplot() + geomPoint(
            sampling = samplingNone
        )
        val layers = p.layers()
        assertEquals(1, layers.size)

        val l = layers[0]

        if (l.sampling == null) {
            Assert.fail("Sampling should not be null")
            return
        }

        val sampling = l.sampling as SamplingOptions
        assertTrue(sampling.isNone)

        assertEquals(EXPECTED_MAP, l.toSpec())
    }

    companion object {
        private val EXPECTED_MAP: Map<String, Any> = mapOf(
            "stat" to "identity",
            "mapping" to emptyMap<String, Any>(),
            "sampling" to "none",
            "position" to "identity",
            "geom" to "point"
        )
    }
}