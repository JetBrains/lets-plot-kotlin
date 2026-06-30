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
        assertTrue("POINT (-73.96 40.772)" in html, "Expected POINT with trimmed noise, got: $html")
        assertTrue("POINT (1 2)" in html, "Expected POINT (1 2), got: $html")
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
        assertTrue("1.0" !in html, "Integer data must not render as 1.0, got: $html")
        assertTrue("E9" !in html, "Large integers must not use scientific notation, got: $html")
        assertTrue("text-align:right;\">1</td>" in html, "Expected compact, right-aligned id 1, got: $html")
        assertTrue("text-align:right;\">1379302771</td>" in html, "Expected plain-decimal pop, got: $html")
        assertTrue("text-align:right;\">id</th>" in html, "Expected right-aligned numeric header, got: $html")
        assertTrue("text-align:left;\">a</td>" in html, "Expected left-aligned text cell, got: $html")
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
        assertTrue("text-align:left;\"></td>" in html, "Expected empty (left-aligned) cell for null, got: $html")
        assertTrue("<a&b>" !in html, "Raw '<a&b>' must not appear in HTML, got: $html")
    }

    @Test
    fun `SpatialDataset escapes the raw geometry fallback in HTML`() {
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
    fun `SpatialDataset with more than 5 rows is truncated with a note`() {
        val code = """
            val n = 25
            val data = mapOf("idx" to (0 until n).map { it.toString() })
            val geometry = (0 until n).map { "{\"type\":\"Point\",\"coordinates\":[0.0,${'$'}it.0]}" }
            SpatialDataset.withGEOJSON(data, geometry)
        """.trimIndent()

        val html = renderHtml(code)
        assertTrue("Showing 5 of 25 rows" in html, "Expected truncation note, got: $html")
        for (i in 0..4) {
            assertTrue(">$i<" in html, "Expected row index $i in HTML")
        }
        assertTrue(">5<" !in html, "Row index 5 must not be rendered")
        val trCount = "<tr".toRegex().findAll(html).count()
        assertEquals(6, trCount, "Expected 1 header + 5 body rows (=6 <tr>), got $trCount")
    }

    private fun assertGeoJsonGeometry(raw: String, expected: String) {
        assertEquals(
            expected,
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON),
            "Unexpected preview for $raw"
        )
    }

    private fun assertGeoJsonFallback(raw: String) {
        assertEquals(
            raw,
            SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON),
            "Expected raw fallback for $raw"
        )
    }

    @Test
    fun `formatGeometryCell formats Point previews compactly`() {
        listOf(
            """{"type":"Point","coordinates":[-73.96,40.772000000000006]}""" to "POINT (-73.96 40.772)",
            """{"type":"Point","coordinates":[-73.123456789,40.5]}""" to "POINT (-73.1235 40.5)",
            """{"type":"Point","coordinates":[1.0,2.0,3.5]}""" to "POINT (1 2 3.5)"
        ).forEach { (raw, expected) ->
            assertGeoJsonGeometry(raw, expected)
        }
    }

    @Test
    fun `formatGeometryCell abbreviates representative GeoJSON geometries`() {
        listOf(
            """{"type":"LineString","coordinates":[[0.0,0.0],[1.0,1.0],[2.5,3.5]]}"""
                    to "LINESTRING (0 0, ...)",
            """{"type":"Polygon","coordinates":[[[0.0,0.0],[10.0,0.0]],[[2.0,2.0],[4.0,2.0]]]}"""
                    to "POLYGON ((0 0, ...), ...)",
            """{"type":"MultiPolygon","coordinates":[[[[0.0,0.0],[1.0,0.0]]],[[[2.0,2.0],[3.0,2.0]]]]}"""
                    to "MULTIPOLYGON (((0 0, ...)), ...)",
            """{"type":"GeometryCollection","geometries":[{"type":"Point","coordinates":[1.0,2.0]},{"type":"LineString","coordinates":[[0.0,0.0],[1.0,1.0]]}]}"""
                    to "GEOMETRYCOLLECTION (POINT (1 2), LINESTRING (0 0, ...))"
        ).forEach { (raw, expected) ->
            assertGeoJsonGeometry(raw, expected)
        }
    }

    @Test
    fun `formatGeometryCell preserves unrenderable GeometryCollection members`() {
        val raw = """{"type":"GeometryCollection","geometries":[
            {"type":"Point","coordinates":[1.0,2.0]},
            {"type":"Mystery","coordinates":[0.0,0.0]},
            {"type":"Point","coordinates":[3.0,null]}
        ]}"""

        val result = SpatialDatasetHtmlRenderer.formatGeometryCell(raw, GeometryFormat.GEOJSON)

        assertTrue(result.startsWith("GEOMETRYCOLLECTION (POINT (1 2), "), "Got: $result")
        assertTrue("Mystery" in result, "Unknown member must be preserved, got: $result")
        assertTrue("null" in result, "Malformed member must be preserved, got: $result")
        assertTrue("\"geometries\"" !in result, "Whole collection must not fall back to raw, got: $result")
    }

    @Test
    fun `formatGeometryCell falls back to raw for unsupported or invalid GeoJSON`() {
        listOf(
            "not a json",
            """{"type":"Mystery","coordinates":[0,0]}""",
            """{"type":"GeometryCollection","geometries":[]}""",
            """{"type":"Point","coordinates":[1.0,null]}""",
            """{"type":"Point","coordinates":[true,1.0]}""",
            """{"type":"Point","coordinates":["1.0","2.0"]}""",
            """{"type":"Point","coordinates":[[1.0,2.0],[3.0,4.0]]}""",
            """{"type":"LineString","coordinates":[5,[0.0,0.0],[1.0,1.0]]}""",
            """{"type":"Point","coordinates":[]}""",
            """{"type":"Point","coordinates":[1.0]}""",
            """{"type":"LineString","coordinates":[]}""",
            """{"type":"Polygon","coordinates":[]}""",
            """{"type":"Polygon","coordinates":[[]]}"""
        ).forEach { raw ->
            assertGeoJsonFallback(raw)
        }
    }

    @Test
    fun `formatGeometryCell passes WKT and WKB through unchanged`() {
        val wkt = "POINT(1 2)"
        val wkb = "0101000000000000000000F03F0000000000000040"

        assertEquals(wkt, SpatialDatasetHtmlRenderer.formatGeometryCell(wkt, GeometryFormat.WKT))
        assertEquals(wkb, SpatialDatasetHtmlRenderer.formatGeometryCell(wkb, GeometryFormat.WKB))
    }
}
