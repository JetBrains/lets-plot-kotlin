/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.jupyter

import org.jetbrains.kotlinx.jupyter.api.MimeTypedResult
import org.jetbrains.kotlinx.jupyter.api.MimeTypes
import org.jetbrains.letsPlot.spatial.GeometryFormat
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.fail

class SpatialDatasetTest : JupyterTest() {

    private fun renderHtml(code: String): String {
        val rendered = execRendered(code) ?: fail("Renderer returned null")
        if (rendered !is MimeTypedResult) {
            fail("Expected MimeTypedResult, got ${rendered::class.qualifiedName}")
        }
        val html = rendered[MimeTypes.HTML]
        assertNotNull(html, "Rendered result has no text/html mime entry")
        return html
    }

    @Test
    fun `SpatialDataset renders as text-html table with headers and pretty geometry`() {
        val code = """
            val data = mapOf("name" to listOf("Alpha", "Beta"))
            val geometry = listOf(
                "{\"type\":\"Point\",\"coordinates\":[-73.96,40.772000000000006]}",
                "{\"type\":\"Point\",\"coordinates\":[1.0,2.0]}"
            )
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("<table" in html, "Expected a <table>, got: $html")
        assertTrue(">name<" in html, "Expected header 'name', got: $html")
        assertTrue(">geometry<" in html, "Expected header 'geometry', got: $html")
        assertTrue(">Alpha<" in html, "Expected value 'Alpha', got: $html")
        assertTrue(">Beta<" in html, "Expected value 'Beta', got: $html")
        // Geometry pretty-printed (floating-point noise trimmed)
        assertTrue("POINT (-73.96 40.772)" in html, "Expected POINT with trimmed noise, got: $html")
        assertTrue("POINT (1 2)" in html, "Expected POINT (1 2), got: $html")
        // Raw GeoJSON must not appear (would also fail HTML escaping check separately)
        assertTrue("40.772000000000006" !in html, "Raw float noise should not appear, got: $html")
        assertTrue("\"type\":\"Point\"" !in html, "Raw GeoJSON should not appear, got: $html")
    }

    @Test
    fun `SpatialDataset renders integer data compactly and right-aligns numeric columns`() {
        val code = """
            val data = mapOf(
                "id" to listOf(1, 2),
                "pop" to listOf(1379302771, 95000),
                "label" to listOf("a", "b")
            )
            val geometry = listOf(
                "{\"type\":\"Point\",\"coordinates\":[0.0,0.0]}",
                "{\"type\":\"Point\",\"coordinates\":[1.0,2.0]}"
            )
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        // Integer-valued numbers render compactly, not as "1.0" or in scientific notation.
        assertTrue("<td class=\"lp-num\">1</td>" in html, "Expected compact, right-aligned id 1, got: $html")
        assertTrue("<td class=\"lp-num\">1379302771</td>" in html, "Expected plain-decimal pop, got: $html")
        assertTrue("1.0" !in html, "Integer data must not render as 1.0, got: $html")
        assertTrue("E9" !in html, "Large integers must not use scientific notation, got: $html")
        // Numeric headers are right-aligned; text columns keep the default (left) alignment.
        assertTrue("<th class=\"lp-num\">id</th>" in html, "Expected right-aligned numeric header, got: $html")
        assertTrue("<td>a</td>" in html, "Expected left-aligned text cell, got: $html")
    }

    @Test
    fun `SpatialDataset truncates long text cells with an ellipsis`() {
        val code = """
            val data = mapOf(
                "long" to listOf("x".repeat(60)),
                "short" to listOf("ok")
            )
            val geometry = listOf("{\"type\":\"Point\",\"coordinates\":[0.0,0.0]}")
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("x".repeat(50) + "..." in html, "Expected long value cut to 50 chars + ellipsis, got: $html")
        assertTrue("x".repeat(51) !in html, "Long value must not exceed the character limit, got: $html")
        assertTrue(">ok<" in html, "Short values must be left intact, got: $html")
    }

    @Test
    fun `SpatialDataset renders columns in declared order with geometry last`() {
        // Column names whose HashMap iteration order differs from insertion order;
        // guards against the column order being lost in data standardization (asPlotData).
        val code = """
            val data = mapOf(
                "name" to listOf("Alpha"),
                "kind" to listOf("culture"),
                "visitors" to listOf(120)
            )
            val geometry = listOf("{\"type\":\"Point\",\"coordinates\":[1.0,2.0]}")
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        val order = listOf(">name<", ">kind<", ">visitors<", ">geometry<").map {
            val i = html.indexOf(it)
            assertTrue(i >= 0, "Expected header $it, got: $html")
            i
        }
        assertEquals(
            order.sorted(), order,
            "Columns must render in declared order (name, kind, visitors) with geometry last, got: $html"
        )
    }

    @Test
    fun `SpatialDataset HTML escapes special characters and renders null as empty`() {
        val code = """
            val data = mapOf(
                "label" to listOf("<a&b>", "x\"y'z", null)
            )
            val geometry = listOf(
                "{\"type\":\"Point\",\"coordinates\":[0.0,0.0]}",
                "{\"type\":\"Point\",\"coordinates\":[1.0,2.0]}",
                "{\"type\":\"Point\",\"coordinates\":[3.0,4.0]}"
            )
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("&lt;a&amp;b&gt;" in html, "Expected escaped '<a&b>', got: $html")
        assertTrue("x&quot;y&#39;z" in html, "Expected escaped quote/apos, got: $html")
        assertTrue("<td></td>" in html, "Expected empty cell for null, got: $html")
        assertTrue("<a&b>" !in html, "Raw '<a&b>' must not appear in HTML, got: $html")
    }

    @Test
    fun `SpatialDataset escapes the raw geometry fallback in HTML`() {
        // An unknown geometry type falls back to the raw string; that raw string must be
        // HTML-escaped when it reaches the table. Pretty-printed WKT never contains HTML
        // metacharacters, so only the raw-fallback path exercises escape() on a geometry cell.
        val code = """
            val data = mapOf("name" to listOf("x"))
            val geometry = listOf("{\"type\":\"Unknown\",\"note\":\"<a>&b\"}")
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("&lt;a&gt;&amp;b" in html, "Raw geometry fallback must be HTML-escaped, got: $html")
        assertTrue("<a>&b" !in html, "Unescaped geometry must not appear, got: $html")
    }

    @Test
    fun `SpatialDataset with more than 20 rows is truncated with a note`() {
        val code = """
            val n = 25
            val data = mapOf("idx" to (0 until n).map { it.toString() })
            val geometry = (0 until n).map { "{\"type\":\"Point\",\"coordinates\":[0.0,${'$'}it.0]}" }
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("Showing 20 of 25 rows" in html, "Expected truncation note, got: $html")
        // First 20 rows present
        for (i in 0..19) {
            assertTrue(">$i<" in html, "Expected row index $i in HTML")
        }
        // 21st row (index 20) must not appear
        assertTrue(">20<" !in html, "Row index 20 must not be rendered")
        // Count rendered <tr> rows (header + 20 body rows = 21)
        val trCount = "<tr".toRegex().findAll(html).count()
        assertEquals(21, trCount, "Expected 1 header + 20 body rows (=21 <tr>), got $trCount")
    }

    @Test
    fun `SpatialDataset row limit is configurable via JupyterConfig`() {
        val code = """
            letsPlotNotebookConfig.spatialDatasetRowLimit = 3
            val n = 5
            val data = mapOf("idx" to (0 until n).map { it.toString() })
            val geometry = (0 until n).map { "{\"type\":\"Point\",\"coordinates\":[0.0,${'$'}it.0]}" }
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("Showing 3 of 5 rows" in html, "Expected configurable truncation note, got: $html")
        val trCount = "<tr".toRegex().findAll(html).count()
        assertEquals(4, trCount, "Expected 1 header + 3 body rows (=4 <tr>), got $trCount")
    }

    // --- Direct unit tests for the geometry pretty-printer ---

    @Test
    fun `formatGeometryCell - Point with float noise is trimmed`() {
        val raw = """{"type":"Point","coordinates":[-73.96,40.772000000000006]}"""
        assertEquals(
            "POINT (-73.96 40.772)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - integer-valued coords have no trailing dot`() {
        val raw = """{"type":"Point","coordinates":[0.0,0.0]}"""
        assertEquals(
            "POINT (0 0)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - trailing zeros trimmed while in-range decimals are kept`() {
        // The "float noise" case only trims digits beyond the 10-decimal cutoff. These values
        // exercise the trimming itself and would catch a regression that dropped significant,
        // in-range fractional digits.
        assertEquals(
            "POINT (1.5 2.25)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(
                """{"type":"Point","coordinates":[1.5,2.25]}""", GeometryFormat.GEOJSON
            )
        )
        assertEquals(
            "POINT (100 0.000001)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(
                """{"type":"Point","coordinates":[100.0,0.000001]}""", GeometryFormat.GEOJSON
            )
        )
        // Nine significant decimals (within the 10-place cutoff) are preserved.
        assertEquals(
            "POINT (-73.123456789 40.5)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(
                """{"type":"Point","coordinates":[-73.123456789,40.5]}""", GeometryFormat.GEOJSON
            )
        )
    }

    @Test
    fun `formatGeometryCell - LineString`() {
        val raw = """{"type":"LineString","coordinates":[[0.0,0.0],[1.0,1.0],[2.5,3.5]]}"""
        assertEquals(
            "LINESTRING (0 0, 1 1, 2.5 3.5)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - MultiPoint`() {
        val raw = """{"type":"MultiPoint","coordinates":[[0.0,0.0],[1.0,2.0]]}"""
        assertEquals(
            "MULTIPOINT ((0 0), (1 2))",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - Polygon with hole`() {
        val raw = """{"type":"Polygon","coordinates":[
            [[0.0,0.0],[10.0,0.0],[10.0,10.0],[0.0,10.0],[0.0,0.0]],
            [[2.0,2.0],[4.0,2.0],[4.0,4.0],[2.0,4.0],[2.0,2.0]]
        ]}"""
        // Each ring has 5 vertices, so it is abbreviated to the first 3 plus an ellipsis;
        // the two-ring (outer + hole) structure is still visible.
        assertEquals(
            "POLYGON ((0 0, 10 0, 10 10, ...), (2 2, 4 2, 4 4, ...))",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - MultiPolygon`() {
        val raw = """{"type":"MultiPolygon","coordinates":[
            [[[0.0,0.0],[1.0,0.0],[1.0,1.0],[0.0,0.0]]],
            [[[2.0,2.0],[3.0,2.0],[3.0,3.0],[2.0,2.0]]]
        ]}"""
        // Each ring has 4 vertices -> abbreviated to the first 3 plus an ellipsis.
        assertEquals(
            "MULTIPOLYGON (((0 0, 1 0, 1 1, ...)), ((2 2, 3 2, 3 3, ...)))",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - GeometryCollection`() {
        val raw = """{"type":"GeometryCollection","geometries":[
            {"type":"Point","coordinates":[1.0,2.0]},
            {"type":"LineString","coordinates":[[0.0,0.0],[1.0,1.0]]}
        ]}"""
        assertEquals(
            "GEOMETRYCOLLECTION (POINT (1 2), LINESTRING (0 0, 1 1))",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - GeometryCollection keeps valid members when a sibling is unrenderable`() {
        // One renderable Point, one unknown-type member, one member with a malformed coordinate.
        // Valid members must still be pretty-printed; the odd ones are kept as raw JSON, not dropped.
        val raw = """{"type":"GeometryCollection","geometries":[
            {"type":"Point","coordinates":[1.0,2.0]},
            {"type":"Mystery","coordinates":[0.0,0.0]},
            {"type":"Point","coordinates":[3.0,null]}
        ]}"""
        val result = SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        assertTrue(
            result.startsWith("GEOMETRYCOLLECTION (POINT (1 2), "),
            "Valid sibling must be pretty-printed, got: $result"
        )
        assertTrue("Mystery" in result, "Unknown-type member must be preserved (raw), got: $result")
        assertTrue("null" in result, "Malformed member must be preserved (raw), got: $result")
        // The whole cell must NOT collapse to the raw input.
        assertTrue("\"geometries\"" !in result, "Cell must not fall back to whole raw, got: $result")
    }

    @Test
    fun `formatGeometryCell - empty GeometryCollection falls back to raw`() {
        val raw = """{"type":"GeometryCollection","geometries":[]}"""
        assertEquals(raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON))
    }

    @Test
    fun `formatGeometryCell - malformed JSON falls back to raw`() {
        val raw = "not a json"
        assertEquals(raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON))
    }

    @Test
    fun `formatGeometryCell - unknown type falls back to raw`() {
        val raw = """{"type":"Mystery","coordinates":[0,0]}"""
        assertEquals(raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON))
    }

    @Test
    fun `formatGeometryCell - WKT and WKB formats pass through unchanged`() {
        val wkt = "POINT(1 2)"
        assertEquals(wkt, SpatialDatasetHtmlRenderer.formatGeometryCell(wkt, GeometryFormat.WKT))
        val wkb = "0101000000000000000000F03F0000000000000040"
        assertEquals(wkb, SpatialDatasetHtmlRenderer.formatGeometryCell(wkb, GeometryFormat.WKB))
    }

    @Test
    fun `formatGeometryCell - Point with 3D coordinate keeps z component`() {
        val raw = """{"type":"Point","coordinates":[1.0,2.0,3.5]}"""
        assertEquals(
            "POINT (1 2 3.5)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - LineString with 3D coordinates keeps z component`() {
        val raw = """{"type":"LineString","coordinates":[[0.0,0.0,1.0],[1.0,1.0,2.0]]}"""
        assertEquals(
            "LINESTRING (0 0 1, 1 1 2)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - Polygon with 3D coordinates keeps z component`() {
        val raw = """{"type":"Polygon","coordinates":[[[0.0,0.0,5.0],[1.0,0.0,5.0],[1.0,1.0,5.0],[0.0,0.0,5.0]]]}"""
        // 4 vertices -> abbreviated; the z component is kept on the vertices that are shown.
        assertEquals(
            "POLYGON ((0 0 5, 1 0 5, 1 1 5, ...))",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    // --- Long geometries are abbreviated to GEOMETRY_PREVIEW_LIMIT items per sequence ---

    @Test
    fun `formatGeometryCell - long vertex sequence is abbreviated with an ellipsis`() {
        val raw = """{"type":"LineString","coordinates":[[0.0,0.0],[1.0,1.0],[2.0,2.0],[3.0,3.0],[4.0,4.0]]}"""
        assertEquals(
            "LINESTRING (0 0, 1 1, 2 2, ...)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - a sequence exactly at the limit is shown in full`() {
        val raw = """{"type":"LineString","coordinates":[[0.0,0.0],[1.0,1.0],[2.0,2.0]]}"""
        assertEquals(
            "LINESTRING (0 0, 1 1, 2 2)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - MultiPolygon with many parts abbreviates the parts as well`() {
        val raw = """{"type":"MultiPolygon","coordinates":[
            [[[0.0,0.0],[1.0,0.0],[0.0,0.0]]],
            [[[2.0,2.0],[3.0,2.0],[2.0,2.0]]],
            [[[4.0,4.0],[5.0,4.0],[4.0,4.0]]],
            [[[6.0,6.0],[7.0,6.0],[6.0,6.0]]]
        ]}"""
        assertEquals(
            "MULTIPOLYGON (((0 0, 1 0, 0 0)), ((2 2, 3 2, 2 2)), ((4 4, 5 4, 4 4)), ...)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - GeometryCollection with many members is abbreviated`() {
        val raw = """{"type":"GeometryCollection","geometries":[
            {"type":"Point","coordinates":[1.0,1.0]},
            {"type":"Point","coordinates":[2.0,2.0]},
            {"type":"Point","coordinates":[3.0,3.0]},
            {"type":"Point","coordinates":[4.0,4.0]}
        ]}"""
        assertEquals(
            "GEOMETRYCOLLECTION (POINT (1 1), POINT (2 2), POINT (3 3), ...)",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    // --- Structurally invalid GeoJSON must fall back to raw, not emit plausible-but-wrong WKT ---

    @Test
    fun `formatGeometryCell - non-numeric coordinates fall back to raw`() {
        // null, boolean, and string-typed coordinates are not valid positions.
        for (raw in listOf(
            """{"type":"Point","coordinates":[1.0,null]}""",
            """{"type":"Point","coordinates":[true,1.0]}""",
            """{"type":"Point","coordinates":["1.0","2.0"]}"""
        )) {
            assertEquals(
                raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON),
                "Expected raw fallback for non-numeric coordinates: $raw"
            )
        }
    }

    @Test
    fun `formatGeometryCell - position that is not an array falls back to raw`() {
        // A scalar where a position array is expected (here in a LineString).
        val raw = """{"type":"LineString","coordinates":[[0.0,0.0],5,[1.0,1.0]]}"""
        assertEquals(raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON))
    }

    @Test
    fun `formatGeometryCell - nested array where a number is expected falls back to raw`() {
        val raw = """{"type":"Point","coordinates":[[1.0,2.0],[3.0,4.0]]}"""
        assertEquals(raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON))
    }

    @Test
    fun `formatGeometryCell - empty or undersized coordinate arrays fall back to raw`() {
        for (raw in listOf(
            """{"type":"Point","coordinates":[]}""",
            """{"type":"Point","coordinates":[1.0]}""",
            """{"type":"LineString","coordinates":[]}""",
            """{"type":"Polygon","coordinates":[]}""",
            """{"type":"Polygon","coordinates":[[]]}"""
        )) {
            assertEquals(
                raw, SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON),
                "Expected raw fallback for empty/undersized coordinates: $raw"
            )
        }
    }
}
