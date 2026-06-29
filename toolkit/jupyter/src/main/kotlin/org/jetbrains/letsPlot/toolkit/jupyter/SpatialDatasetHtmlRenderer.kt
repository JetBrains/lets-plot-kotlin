/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.jupyter

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import org.jetbrains.letsPlot.spatial.GeometryFormat
import org.jetbrains.letsPlot.spatial.SpatialDataset
import java.util.Locale

internal object SpatialDatasetHtmlRenderer {

    const val ROW_LIMIT: Int = 20

    fun render(dataset: SpatialDataset): String {
        val columns: List<String> = dataset.keys.toList()
        val totalRows: Int = dataset.values.maxOfOrNull { it.size } ?: 0
        val visibleRows: Int = minOf(totalRows, ROW_LIMIT)
        val geometryKey: String = dataset.geometryKey
        val geometryFormat: GeometryFormat = dataset.geometryFormat

        val sb = StringBuilder()
        sb.append("<div style=\"font-family: sans-serif; font-size: 12px;\">")
        sb.append("<table style=\"border-collapse: collapse; border: 1px solid #ccc;\">")

        sb.append("<thead><tr>")
        for (name in columns) {
            sb.append("<th style=\"border: 1px solid #ccc; padding: 4px 8px; background: #f5f5f5; text-align: left;\">")
            sb.append(escape(name))
            sb.append("</th>")
        }
        sb.append("</tr></thead>")

        sb.append("<tbody>")
        for (row in 0 until visibleRows) {
            sb.append("<tr>")
            for (name in columns) {
                val series = dataset[name]
                val cell = if (series != null && row < series.size) series[row] else null
                sb.append("<td style=\"border: 1px solid #ccc; padding: 4px 8px; vertical-align: top; text-align: left;\">")
                if (cell != null) {
                    val display = if (name == geometryKey) {
                        formatGeometryCell(cell.toString(), geometryFormat)
                    } else {
                        cell.toString()
                    }
                    sb.append(escape(display))
                }
                sb.append("</td>")
            }
            sb.append("</tr>")
        }
        sb.append("</tbody>")
        sb.append("</table>")

        if (totalRows > ROW_LIMIT) {
            sb.append("<div style=\"margin-top: 4px; color: #666;\">")
            sb.append("Showing ").append(ROW_LIMIT).append(" of ").append(totalRows).append(" rows")
            sb.append("</div>")
        }

        sb.append("</div>")
        return sb.toString()
    }

    internal fun formatGeometryCell(raw: String, format: GeometryFormat): String {
        if (format != GeometryFormat.GEOJSON) return raw
        return try {
            val element = Json.parseToJsonElement(raw)
            formatGeoJson(element) ?: raw
        } catch (_: SerializationException) {
            raw
        }
    }

    private fun formatGeoJson(element: JsonElement): String? {
        val obj = element as? JsonObject ?: return null
        val type = (obj["type"] as? JsonPrimitive)?.contentOrNull ?: return null
        return when (type) {
            "Point" -> formatPosition(coordsOf(obj) ?: return null)?.let { "POINT ($it)" }
            "MultiPoint" -> formatPositionList(coordsOf(obj) ?: return null, parenthesize = true)?.let { "MULTIPOINT ($it)" }
            "LineString" -> formatPositionList(coordsOf(obj) ?: return null)?.let { "LINESTRING ($it)" }
            "MultiLineString" -> formatRingList(coordsOf(obj) ?: return null)?.let { "MULTILINESTRING ($it)" }
            "Polygon" -> formatRingList(coordsOf(obj) ?: return null)?.let { "POLYGON ($it)" }
            "MultiPolygon" -> formatPolygonList(coordsOf(obj) ?: return null)?.let { "MULTIPOLYGON ($it)" }
            "GeometryCollection" -> {
                val geoms = obj["geometries"] as? JsonArray ?: return null
                if (geoms.isEmpty()) return null
                val parts = geoms.map { formatGeoJson(it) ?: it.toString() }
                "GEOMETRYCOLLECTION (${parts.joinToString(", ")})"
            }
            else -> null
        }
    }

    private fun coordsOf(obj: JsonObject): JsonArray? = obj["coordinates"] as? JsonArray

    private fun formatPosition(coords: JsonArray): String? {
        if (coords.size < 2) return null
        return coords.map { formatNumber(it) ?: return null }.joinToString(" ")
    }

    private fun formatPositionList(coords: JsonArray, parenthesize: Boolean = false): String? {
        if (coords.isEmpty()) return null
        return coords.map { item ->
            val arr = item as? JsonArray ?: return null
            val position = formatPosition(arr) ?: return null
            if (parenthesize) "($position)" else position
        }.joinToString(", ")
    }

    private fun formatRingList(rings: JsonArray): String? {
        if (rings.isEmpty()) return null
        return rings.map { item ->
            val arr = item as? JsonArray ?: return null
            "(${formatPositionList(arr) ?: return null})"
        }.joinToString(", ")
    }

    private fun formatPolygonList(polygons: JsonArray): String? {
        if (polygons.isEmpty()) return null
        return polygons.map { item ->
            val arr = item as? JsonArray ?: return null
            "(${formatRingList(arr) ?: return null})"
        }.joinToString(", ")
    }

    private fun formatNumber(element: JsonElement): String? {
        val p = element as? JsonPrimitive ?: return null
        if (p.isString) return null
        val d = p.doubleOrNull ?: return null
        return formatDouble(d)
    }

    private fun formatDouble(d: Double): String {
        if (d.isNaN() || d.isInfinite()) return d.toString()
        var s = String.format(Locale.ROOT, "%.10f", d)
        if ('.' in s) {
            s = s.trimEnd('0').trimEnd('.')
        }
        if (s.isEmpty() || s == "-") s = "0"
        return s
    }

    private fun escape(s: String): String {
        val out = StringBuilder(s.length)
        for (ch in s) {
            when (ch) {
                '&' -> out.append("&amp;")
                '<' -> out.append("&lt;")
                '>' -> out.append("&gt;")
                '"' -> out.append("&quot;")
                '\'' -> out.append("&#39;")
                else -> out.append(ch)
            }
        }
        return out.toString()
    }
}
