/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.server.config.transform.PlotConfigServerSideTransforms
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.intern.toSpec
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DataVectorsTest(
    private val inputData: Map<String, Any>,
    private val expectedData: Map<String, List<*>>
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            // Lists
            val bytes = listOf<Byte?>(1, 2, null)
            val shorts = listOf<Short?>(1, 2, null)
            val ints = listOf<Int?>(1, 2, null)
            val longs = listOf<Long?>(1L, 2L, null)
            val floats = listOf<Float?>(1.0f, 2.0f, null)
            val doubles = listOf<Double?>(1.0, 2.0, null)
            val anyNums = listOf<Any?>(1, 2.0f, null)

            // Expected after standardisation
            val expectedList = listOf<Double?>(1.0, 2.0, null)
            val expectedMap = mapOf(
                "bytes" to expectedList,
                "shorts" to expectedList,
                "ints" to expectedList,
                "longs" to expectedList,
                "floats" to expectedList,
                "doubles" to expectedList,
                "anyNums" to expectedList
            )

            // Arrays
            val byteArr = byteArrayOf(1, 2)
            val shortArr = shortArrayOf(1, 2)
            val intArr = intArrayOf(1, 2)
            val longArr = longArrayOf(1L, 2L)
            val floatArr = floatArrayOf(1.0f, 2.0f)
            val doubleArr = doubleArrayOf(1.0, 2.0)
            val anyNumArr = arrayOf<Any?>(1, 2.0f, null)

            val expectedList2 = listOf<Double>(1.0, 2.0)
            val expectedMap2 = mapOf(
                "bytes" to expectedList2,
                "shorts" to expectedList2,
                "ints" to expectedList2,
                "longs" to expectedList2,
                "floats" to expectedList2,
                "doubles" to expectedList2,
                "anyNums" to expectedList
            )

            return listOf<Array<Any>>(
                arrayOf(
                    // lists
                    mapOf(
                        "bytes" to bytes,
                        "shorts" to shorts,
                        "ints" to ints,
                        "longs" to longs,
                        "floats" to floats,
                        "doubles" to doubles,
                        "anyNums" to anyNums
                    ),
                    expectedMap
                ),
                arrayOf(
                    // sequences
                    mapOf(
                        "bytes" to bytes.asSequence(),
                        "shorts" to shorts.asSequence(),
                        "ints" to ints.asSequence(),
                        "longs" to longs.asSequence(),
                        "floats" to floats.asSequence(),
                        "doubles" to doubles.asSequence(),
                        "anyNums" to anyNums.asSequence()
                    ),
                    expectedMap
                ),
                arrayOf(
                    // iterables
                    mapOf(
                        "bytes" to bytes.asSequence().asIterable(),
                        "shorts" to shorts.asSequence().asIterable(),
                        "ints" to ints.asSequence().asIterable(),
                        "longs" to longs.asSequence().asIterable(),
                        "floats" to floats.asSequence().asIterable(),
                        "doubles" to doubles.asSequence().asIterable(),
                        "anyNums" to anyNums.asSequence().asIterable()
                    ),
                    expectedMap
                ),
                arrayOf(
                    // arrays
                    mapOf(
                        "bytes" to byteArr,
                        "shorts" to shortArr,
                        "ints" to intArr,
                        "longs" to longArr,
                        "floats" to floatArr,
                        "doubles" to doubleArr,
                        "anyNums" to anyNumArr
                    ),
                    expectedMap2
                )
            )
        }
    }

    @Test
    fun `data in ggplot`() {
        val p = ggplot(data = inputData) + geom_point()

        val processedSpec = applyEntryTransforms(p.toSpec())

        @Suppress("UNCHECKED_CAST")
        val standardisedData = processedSpec[Option.PlotBase.DATA] as Map<String, Any>

        Assert.assertEquals(expectedData, standardisedData)
    }

    @Test
    fun `data in layer`() {
        val p = ggplot() + geom_point(data = inputData)

        val processedSpec = applyEntryTransforms(p.toSpec())

        @Suppress("UNCHECKED_CAST")
        val layerSpec = (processedSpec[Option.Plot.LAYERS] as List<*>).first() as Map<String, Any>

        @Suppress("UNCHECKED_CAST")
        val standardisedData = layerSpec[Option.PlotBase.DATA] as Map<String, Any>

        Assert.assertEquals(expectedData, standardisedData)
    }

    private fun applyEntryTransforms(rawSpec: MutableMap<String, Any>): MutableMap<String, Any> {
        return PlotConfigServerSideTransforms.entryTransform().apply(rawSpec)
    }
}