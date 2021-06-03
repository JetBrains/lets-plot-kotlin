/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.spatial

import jetbrains.letsPlot.intern.asPlotData

class SpatialDataset private constructor(
    private val map: Map<String, List<Any?>>,
    val geometryKey: String,
    val geometryFormat: GeometryFormat
) : Map<String, List<Any?>> by map {

    override fun toString(): String {
        return "SpatialDataset($geometryFormat, key='$geometryKey', map=${this.map})"
    }

    companion object {
        private val GEO_COL_NAMES = listOf(
            "geometry", "shape", "coord", "coordinates"
        )

        fun withGEOJSON(
            data: Map<String, Any>,
            geometry: List<String>
        ): SpatialDataset {
            return create(data, geometry, GeometryFormat.GEOJSON)
        }

        /**
         * WKT is not yet supported by Lets-plot
         */
        fun withWKT(
            data: Map<String, Any>,
            geometry: List<String>
        ): SpatialDataset {
            return create(data, geometry, GeometryFormat.WKT)
        }

        /**
         * WKB is not yet supported by Lets-plot
         */
        fun withWKB(
            data: Map<String, Any>,
            geometry: List<String>
        ): SpatialDataset {
            return create(data, geometry, GeometryFormat.WKB)
        }

        private fun create(
            data: Map<String, Any>,
            geometry: List<String>,
            geometryFormat: GeometryFormat
        ): SpatialDataset {
            @Suppress("NAME_SHADOWING")
            val data: Map<String, List<Any?>> = asPlotData(data)

            data.entries.forEach {
                require(it.value.size == geometry.size) {
                    "The size of data series '${it.key}' (${it.value.size}) must be equal to the size geometry collection: ${geometry.size}."
                }
            }

            // ToDo: check the geometry format

            val geometryKey = chooseGeometryColName(data.keys)
            val map = data + mapOf(geometryKey to geometry)
            return SpatialDataset(map, geometryKey, geometryFormat)
        }

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
    }
}