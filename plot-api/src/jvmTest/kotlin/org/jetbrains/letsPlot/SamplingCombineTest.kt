/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.sampling.samplingNone
import org.jetbrains.letsPlot.sampling.samplingPick
import org.jetbrains.letsPlot.sampling.samplingRandom
import org.jetbrains.letsPlot.sampling.samplingSystematic
import org.junit.Assert.assertEquals
import org.junit.Test

class SamplingCombineTest {

    @Test
    fun `combine two samplings into a feature-list`() {
        val p = ggplot() + geomPoint(
            sampling = samplingRandom(500, 42) + samplingSystematic(100)
        )
        val l = p.layers().single()

        val expected = mapOf(
            "feature-list" to listOf(
                mapOf("sampling" to mapOf("n" to 500.0, "seed" to 42.0, "name" to "random")),
                mapOf("sampling" to mapOf("n" to 100.0, "name" to "systematic"))
            )
        )
        assertEquals(expected, l.toSpec()["sampling"])
    }

    @Test
    fun `chained combine flattens into a single feature-list preserving order`() {
        val sampling = samplingRandom(500, 42) + samplingSystematic(100) + samplingPick(10)
        val p = ggplot() + geomPoint(sampling = sampling)
        val l = p.layers().single()

        val expected = mapOf(
            "feature-list" to listOf(
                mapOf("sampling" to mapOf("n" to 500.0, "seed" to 42.0, "name" to "random")),
                mapOf("sampling" to mapOf("n" to 100.0, "name" to "systematic")),
                mapOf("sampling" to mapOf("n" to 10.0, "name" to "pick"))
            )
        )
        assertEquals(expected, l.toSpec()["sampling"])
    }

    @Test(expected = IllegalArgumentException::class)
    fun `combining samplingNone on the right throws`() {
        samplingRandom(10) + samplingNone
    }

    @Test(expected = IllegalArgumentException::class)
    fun `combining samplingNone on the left throws`() {
        samplingNone + samplingRandom(10)
    }
}
