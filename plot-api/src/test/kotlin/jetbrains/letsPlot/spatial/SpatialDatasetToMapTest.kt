/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.spatial

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SpatialDatasetTest(
    private val dataColumns: List<String>,
    private val expectedGeometryKey: String
) {

    @Test
    fun asMap() {
        val points = listOf("POINT (1 2)", "POINT (1 3)")
        val dataSerie = listOf(0.0, 0.0)
        val data = HashMap<String, Any>()
        for (columnName in dataColumns) {
            data[columnName] = dataSerie
        }
        val sds = SpatialDataset.fromWKT(
            data = data,
            geometry = points
        )

        assertEquals(expectedGeometryKey, sds.geometryKey)
        assertEquals(sds[expectedGeometryKey], points)

        val expectedGeometryColumn = mapOf(expectedGeometryKey to points)
        assertEquals(
            data + expectedGeometryColumn,
            sds
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any?>> {
            return listOf<Array<Any?>>(
                arrayOf(emptyList<String>(), "geometry"),
                arrayOf(listOf("A", "B"), "geometry"),
                arrayOf(listOf("A", "geometry"), "shape"),
                arrayOf(listOf("A", "geometry", "shape"), "coord"),
                arrayOf(listOf("A", "geometry", "shape", "coord"), "coordinates"),
                arrayOf(listOf("A", "geometry", "shape", "coord", "coordinates"), "geometry_1"),
                arrayOf(
                    listOf(
                        "geometry", "shape", "coord", "coordinates",
                        "geometry_1", "shape_1", "coord_1"
                    ),
                    "coordinates_1"
                ),
                arrayOf(
                    listOf(
                        "geometry", "shape", "coord", "coordinates",
                        "geometry_1", "shape_1", "coord_1", "coordinates_1"
                    ),
                    "geometry_2"
                ),
                arrayOf(listOf(""), "geometry")
            )
        }
    }
}