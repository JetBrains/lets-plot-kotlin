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

    private const val STYLE: String =
        "<style>" +
            ".lp-spatial-dataset{font-family:sans-serif;font-size:12px;}" +
            ".lp-spatial-dataset table{border-collapse:collapse;border:1px solid #ccc;}" +
            ".lp-spatial-dataset th,.lp-spatial-dataset td{border:1px solid #ccc;padding:4px 8px;text-align:left;}" +
            ".lp-spatial-dataset th{background:#f5f5f5;}" +
            ".lp-spatial-dataset td{vertical-align:top;}" +
            ".lp-spatial-dataset .lp-spatial-note{margin-top:4px;color:#666;}" +
            "</style>"

    fun render(dataset: SpatialDataset, rowLimit: Int = ROW_LIMIT): String {
        // Resolve each column's series once; all series share the same length (enforced by
        // SpatialDataset.create), so the first one gives the row count.
        val columns: List<Pair<String, List<Any?>>> = dataset.keys.map { it to dataset.getValue(it) }
        val totalRows: Int = columns.firstOrNull()?.second?.size ?: 0
        val visibleRows: Int = minOf(totalRows, rowLimit)
        val geometryKey: String = dataset.geometryKey
        val geometryFormat: GeometryFormat = dataset.geometryFormat

        val sb = StringBuilder()
        sb.append("<div class=\"lp-spatial-dataset\">")
        sb.append(STYLE)
        sb.append("<table>")

        sb.append("<thead><tr>")
        for ((name, _) in columns) {
            sb.append("<th>").append(escape(name)).append("</th>")
        }
        sb.append("</tr></thead>")

        sb.append("<tbody>")
        for (row in 0 until visibleRows) {
            sb.append("<tr>")
            for ((name, series) in columns) {
                sb.append("<td>")
                val cell = series[row]
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

        if (totalRows > visibleRows) {
            sb.append("<div class=\"lp-spatial-note\">")
            sb.append("Showing ").append(visibleRows).append(" of ").append(totalRows).append(" rows")
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

    // Formats each element of [array] - which must itself be a JSON array - via [transform] and
    // joins the results with ", ". Returns null (so the caller falls back to the raw string) if the
    // array is empty, an element is not an array, or [transform] rejects an element.
    private fun joinArrays(array: JsonArray, transform: (JsonArray) -> String?): String? {
        if (array.isEmpty()) return null
        val parts = ArrayList<String>(array.size)
        for (item in array) {
            val arr = item as? JsonArray ?: return null
            parts.add(transform(arr) ?: return null)
        }
        return parts.joinToString(", ")
    }

    private fun formatPositionList(coords: JsonArray, parenthesize: Boolean = false): String? =
        joinArrays(coords) { position -> formatPosition(position)?.let { if (parenthesize) "($it)" else it } }

    private fun formatRingList(rings: JsonArray): String? =
        joinArrays(rings) { ring -> formatPositionList(ring)?.let { "($it)" } }

    private fun formatPolygonList(polygons: JsonArray): String? =
        joinArrays(polygons) { polygon -> formatRingList(polygon)?.let { "($it)" } }

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
