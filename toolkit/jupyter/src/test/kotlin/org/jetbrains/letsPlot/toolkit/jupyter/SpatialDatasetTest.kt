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
        assertTrue("<td style=\"border: 1px solid #ccc; padding: 4px 8px; vertical-align: top; text-align: left;\"></td>" in html,
            "Expected empty cell for null, got: $html")
        assertTrue("<a&b>" !in html, "Raw '<a&b>' must not appear in HTML, got: $html")
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
        assertEquals(
            "POLYGON ((0 0, 10 0, 10 10, 0 10, 0 0), " +
                    "(2 2, 4 2, 4 4, 2 4, 2 2))",
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)
        )
    }

    @Test
    fun `formatGeometryCell - MultiPolygon`() {
        val raw = """{"type":"MultiPolygon","coordinates":[
            [[[0.0,0.0],[1.0,0.0],[1.0,1.0],[0.0,0.0]]],
            [[[2.0,2.0],[3.0,2.0],[3.0,3.0],[2.0,2.0]]]
        ]}"""
        assertEquals(
            "MULTIPOLYGON (((0 0, 1 0, 1 1, 0 0)), " +
                    "((2 2, 3 2, 3 3, 2 2)))",
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
}
