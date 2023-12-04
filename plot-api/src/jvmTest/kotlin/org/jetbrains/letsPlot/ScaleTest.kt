package org.jetbrains.letsPlot

import junit.framework.TestCase
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.PlotAssert
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.scale.scaleColorManual
import org.jetbrains.letsPlot.scale.scaleXContinuous
import org.jetbrains.letsPlot.scale.scaleXDiscrete
import org.junit.Test

class ScaleTest {

    @Test
    fun useLabelsDict() {
        val p = ggplot() + scaleXContinuous(
            labels = mapOf(1 to "A", 2 to "B", 3 to "C")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "x",
                "breaks" to listOf(1.0, 2.0, 3.0),
                "labels" to listOf("A", "B", "C")
            )
        )
    }

    @Test
    fun useBreaksDict() {
        val p = ggplot() + scaleXContinuous(
            breaks = mapOf("A" to 1, "B" to 2, "C" to 3),
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "x",
                "breaks" to listOf(1.0, 2.0, 3.0),
                "labels" to listOf("A", "B", "C")
            )
        )
    }

    @Test
    fun `use 'labels'-dict with specified 'breaks'`() {
        val p = ggplot() + scaleXDiscrete(
            labels = mapOf("a" to "A", "b" to "B", "c" to "C"),
            breaks = listOf("a", "d", "c")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "x",
                "discrete" to true,
                "breaks" to listOf("a", "c", "d"),
                "labels" to listOf("A", "C")
            )
        )
    }

    @Test
    fun `'labels'-dict with no matches to the specified 'breaks'`() {
        val p = ggplot() + scaleXDiscrete(
            labels = mapOf("a" to "A", "b" to "B", "c" to "C"),
            breaks = listOf("d", "e")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "x",
                "discrete" to true,
                "breaks" to listOf("d", "e"),
                "labels" to emptyList<String>()
            )
        )
    }

    // Manual

    @Test
    fun useValuesDict() {
        val p = ggplot() + scaleColorManual(
            values = mapOf("a" to "A", "b" to "B", "c" to "C")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "color",
                "breaks" to listOf("a", "b", "c"),
                "values" to listOf("A", "B", "C")
            )
        )
    }

    @Test
    fun `use 'values'-dict with specified 'breaks'`() {
        val p = ggplot() + scaleColorManual(
            values = mapOf("a" to "A", "b" to "B", "c" to "C"),
            breaks = listOf("a", "c")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "color",
                "breaks" to listOf("a", "c"),
                "values" to listOf("A", "C", "B")
            )
        )
    }

    @Test
    fun `use 'values'-dict with specified 'breaks' and 'limits'`() {
        val p = ggplot() + scaleColorManual(
            values = mapOf("a" to "A", "b" to "B", "c" to "C"),
            breaks = listOf("a", "c"),
            limits = listOf("b", "c")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "color",
                "breaks" to listOf("a", "c"),
                "limits" to listOf("b", "c"),
                "values" to listOf("B", "C", "A")
            )
        )
    }

    @Test
    fun `'values'-dict with no matches to the specified 'breaks'`() {
        val p = ggplot() + scaleColorManual(
            values = mapOf("a" to "A", "b" to "B", "c" to "C"),
            breaks = listOf("d", "e")
        )
        assertScalesSpec(
            p,
            mapOf(
                "aesthetic" to "color",
                "breaks" to listOf("d", "e")
            )
        )
    }

    private fun assertScalesSpec(
        p: Plot,
        expected: Map<String, Any>,
    ) {
        PlotAssert.assertThat(p).features().length(1)
        val spec = p.toSpec()
        TestCase.assertEquals(
            listOf(expected),
            spec[Option.Plot.SCALES]
        )
    }
}