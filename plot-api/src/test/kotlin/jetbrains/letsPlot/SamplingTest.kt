/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.sampling.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SamplingTest(
    private val samplingOptions: SamplingOptions,
    private val expectedMap: Map<String, Any>
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    samplingRandom(100, 5000),
                    mapOf(
                        "n" to 100,
                        "seed" to 5000,
                        "name" to "random"
                    )
                ),
                arrayOf(
                    samplingGroupRandom(101, 5001),
                    mapOf(
                        "n" to 101,
                        "seed" to 5001,
                        "name" to "group_random"
                    )
                ),
                arrayOf(
                    samplingRandomStratified(10, 0, 5),
                    mapOf(
                        "n" to 10,
                        "name" to "random_stratified",
                        "seed" to 0,
                        "min_subsample" to 5
                    )
                ),
                arrayOf(
                    samplingPick(100),
                    mapOf(
                        "n" to 100,
                        "name" to "pick"
                    )
                ),
                arrayOf(
                    samplingSystematic(42),
                    mapOf(
                        "n" to 42,
                        "name" to "systematic"
                    )
                ),
                arrayOf(
                    samplingGroupSystematic(43),
                    mapOf(
                        "n" to 43,
                        "name" to "group_systematic"
                    )
                ),
                arrayOf(
                    samplingVertexVW(44),
                    mapOf(
                        "n" to 44,
                        "name" to "vertex_vw"
                    )
                ),
                arrayOf(
                    samplingVertexDP(45),
                    mapOf(
                        "n" to 45,
                        "name" to "vertex_dp"
                    )
                )
            )
        }
    }

    @Test
    fun `sampling with parameters`() {
        val p = ggplot() + geomPoint(
            sampling = samplingOptions
        )
        val layers = p.layers()
        Assert.assertEquals(1, layers.size)

        val l = layers[0]

        if (l.sampling == null) {
            Assert.fail("Sampling should not be null")
            return
        }

        val sampling = l.sampling as SamplingOptions

        Assert.assertEquals(expectedMap, sampling.mapping.map)
    }
}