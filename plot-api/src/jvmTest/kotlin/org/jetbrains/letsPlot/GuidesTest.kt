package org.jetbrains.letsPlot

import junit.framework.TestCase
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.label.labsAlt
import org.jetbrains.letsPlot.scale.guideColorbar
import org.jetbrains.letsPlot.scale.guideLegend
import org.jetbrains.letsPlot.scale.guides
import org.jetbrains.letsPlot.scale.guidesAlt
import org.junit.Test

class GuidesTest {

    @Test
    fun mergeTwoGuides() {
        val p = ggplot() + guides(color = guideLegend(nrow = 1)) + guides(color = guideLegend(title = "Title"))
        assertGuidesSpec(
            p,
            mapOf(
                "color" to mapOf(
                    "name" to "legend",
                    "nrow" to 1.0,
                    "title" to "Title"
                )
            )
        )
    }

    @Test
    fun mergeShapeAndColorGuides() {
        val p = ggplot() +
                guides(shape = guideLegend("Shape title", ncol = 2)) +
                guides(color = guideColorbar("Color title", nbin = 8))
        assertGuidesSpec(
            p,
            mapOf(
                "shape" to mapOf(
                    "name" to "legend",
                    "title" to "Shape title",
                    "ncol" to 2.0
                ),
                "color" to mapOf(
                    "name" to "colorbar",
                    "title" to "Color title",
                    "nbin" to 8.0
                )
            )
        )
    }

    @Test
    fun labsTest() {
        val p = ggplot() + labs(x = "x title")
        assertGuidesSpec(
            p,
            mapOf(
                "x" to mapOf(
                    "title" to "x title"
                )
            )
        )
    }

    @Test
    fun guidesAndLabsTest() {
        val p = ggplot() + guides(color = guideLegend(nrow = 1)) + labs(color = "Title")
        assertGuidesSpec(
            p,
            mapOf(
                "color" to mapOf(
                    "name" to "legend",
                    "nrow" to 1.0,
                    "title" to "Title"
                )
            )
        )
    }

    @Test
    fun plotAndAxisTitles() {
        val p = ggplot() + labs(title = "Plot title", x = "x title")
        assertGuidesSpec(
            p,
            mapOf(
                "x" to mapOf(
                    "title" to "x title"
                )
            )
        )
        // plot title
        TestCase.assertEquals(
            mapOf("text" to "Plot title"),
            p.toSpec()[Option.Plot.TITLE]
        )
    }

    @Test
    fun titlesForNamedElements() {
        val p = ggplot() + labsAlt("a" to "A title", "b"  to "B title")
        assertGuidesSpec(
            p,
            mapOf(
                "a" to mapOf(
                    "title" to "A title"
                ),
                "b" to mapOf(
                    "title" to "B title"
                )
            )
        )
    }

    @Test
    fun guidesForNamedElements() {
        val p = ggplot() + guidesAlt(
            "a" to guideLegend("A title", nrow = 1),
            "b" to guideLegend("B title")
        )
        assertGuidesSpec(
            p,
            mapOf(
                "a" to mapOf(
                    "name" to "legend",
                    "title" to "A title",
                    "nrow" to 1.0
                ),
                "b" to mapOf(
                    "name" to "legend",
                    "title" to "B title"
                )
            )
        )
    }

    @Test
    fun labsAndGuidesForNamedElements() {
        val p = ggplot() + labsAlt("a" to "A title", "b" to "B title") + guidesAlt(
            "a" to guideLegend(nrow = 1),
            "c" to guideLegend("C title")
        )
        assertGuidesSpec(
            p,
            mapOf(
                "a" to mapOf(
                    "name" to "legend",
                    "title" to "A title",
                    "nrow" to 1.0
                ),
                "b" to mapOf(
                     "title" to "B title"
                ),
                "c" to mapOf(
                    "name" to "legend",
                    "title" to "C title"
                )
            )
        )
    }

    private fun assertGuidesSpec(
        p: Plot,
        expected: Map<String, Any>,
    ) {
        val spec = p.toSpec()
        TestCase.assertTrue(Option.Plot.GUIDES in spec)
        TestCase.assertEquals(
            expected,
            spec[Option.Plot.GUIDES]
        )
    }
}