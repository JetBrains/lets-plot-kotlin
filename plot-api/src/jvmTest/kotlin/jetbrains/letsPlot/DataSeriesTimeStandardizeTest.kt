/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.server.config.transform.PlotConfigServerSideTransforms
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.toSpec
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneId.SHORT_IDS
import java.time.ZonedDateTime
import java.util.*

@RunWith(Parameterized::class)
class DataSeriesTimeStandardizeTest(
    private val inputData: Map<String, Any>,
    private val expectedData: Map<String, List<*>>
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            val zonedDateTime0 = ZonedDateTime.of(
                2020,
                6,
                10,
                13,
                58,
                0,
                0,
                ZoneId.of("EST", SHORT_IDS)
            )

            val zonedDateTime1 = ZonedDateTime.of(
                2020,
                6,
                10,
                14,
                4,
                0,
                0,
                ZoneId.of("EST", SHORT_IDS)
            )

            val instant0 = zonedDateTime0.toInstant().toEpochMilli()
            val instant1 = zonedDateTime1.toInstant().toEpochMilli()


            // Input date-time series
            val dateTimes = listOf<Date?>(Date(instant0), Date(instant1), null)
            val instants = listOf<Instant?>(zonedDateTime0.toInstant(), zonedDateTime1.toInstant(), null)
            val zonedDateTimes = listOf<ZonedDateTime?>(zonedDateTime0, zonedDateTime1, null)

            // Expected after standardisation
            val expectedList = listOf<Double?>(1591815480000.0, 1591815840000.0, null)
            val expectedMap = mapOf(
                "dateTimes" to expectedList,
                "instants" to expectedList,
                "zonedDateTimes" to expectedList
            )

            // Test data
            return listOf<Array<Any>>(
                arrayOf(
                    // lists
                    mapOf(
                        "dateTimes" to dateTimes,
                        "instants" to instants,
                        "zonedDateTimes" to zonedDateTimes
                    ),
                    expectedMap
                )
            )
        }
    }

    @Test
    fun `data in ggplot`() {
        val p = ggplot(data = inputData) + geomPoint()

        val processedSpec = applyEntryTransforms(p.toSpec())

        @Suppress("UNCHECKED_CAST")
        val standardisedData = processedSpec[Option.PlotBase.DATA] as Map<String, Any>

        Assert.assertEquals(expectedData, standardisedData)
    }

    @Test
    fun `data in layer`() {
        val p = ggplot() + geomPoint(data = inputData)

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