/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.spatial

import jetbrains.letsPlot.intern.asPlotData

class SpatialDataset private constructor(
    val data: Map<String, List<Any?>>,
    val geometry: List<String>,
    val geometryFormat: GeometryFormat
) {

    /**
     * @return Pair - Map<String, List> and the 'geometry key'
     */
    fun toMap(): Pair<Map<String, List<Any?>>, String> {
        val geometryKey = chooseGeometryColName(data.keys)
        return Pair(
            data + mapOf(geometryKey to geometry),
            geometryKey
        )
    }

    companion object {
        private val GEO_COL_NAMES = listOf(
            "geometry", "shape", "coord", "coordinates"
        )

        private fun chooseGeometryColName(usedNames: Set<String>): String {
            var i = 0
            while (true) {
                val name = GEO_COL_NAMES.map { if (i == 0) it else it + "_$i" }.find { !(it in usedNames) }
                if (name != null) {
                    return name
                }
                i++
            }
        }

        fun fromGEOJSON(
            data: Map<String, *>? = null,
            geometry: List<String>
        ): SpatialDataset {
            return create(data, geometry, GeometryFormat.GEOJSON)
        }

        /**
         * Not yet supported
         */
        fun fromWKT(
            data: Map<String, *>? = null,
            geometry: List<String>
        ): SpatialDataset {
            return create(data, geometry, GeometryFormat.WKT)
        }

        /**
         * Not yet supported
         */
        fun fromWKB(
            data: Map<String, *>? = null,
            geometry: List<String>
        ): SpatialDataset {
            return create(data, geometry, GeometryFormat.WKB)
        }

        private fun create(
            rawData: Map<String, *>? = null,
            geometry: List<String>,
            geometryFormat: GeometryFormat
        ): SpatialDataset {

            val data: Map<String, List<Any?>> = rawData?.let { asPlotData(rawData) } ?: emptyMap()

            data.entries.forEach {
                require(it.value.size == geometry.size) {
                    "The size of data series '${it.key}' (${it.value.size}) must be equal to the size geometry collection: ${geometry.size}."
                }
            }

            // ToDo: check the geometry format

            return SpatialDataset(data, geometry, geometryFormat)
        }
    }
}