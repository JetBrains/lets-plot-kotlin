package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.intern.ColorScale
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ColorScalePaletteTest {

    @Test
    fun `color scale functions return ColorScale`() {
        assertIs<ColorScale>(scaleColorBrewer())
        assertIs<ColorScale>(scaleFillBrewer())
        assertIs<ColorScale>(scaleColorViridis())
        assertIs<ColorScale>(scaleFillViridis())
        assertIs<ColorScale>(scaleColorGradient(low = "red", high = "blue"))
        assertIs<ColorScale>(scaleFillGradient(low = "red", high = "blue"))
        assertIs<ColorScale>(scaleColorGrey())
        assertIs<ColorScale>(scaleFillGrey())
        assertIs<ColorScale>(scaleColorDiscrete())
        assertIs<ColorScale>(scaleFillDiscrete())
        assertIs<ColorScale>(scaleColorContinuous())
        assertIs<ColorScale>(scaleFillContinuous())
        assertIs<ColorScale>(scaleColorManual(values = listOf("red", "green", "blue")))
        assertIs<ColorScale>(scaleFillManual(values = listOf("red", "green", "blue")))
    }

    @Test
    fun `brewer palette generates expected number of colors`() {
        val scale = scaleColorBrewer(palette = "Set1")
        val colors = scale.palette(5)
        assertEquals(5, colors.size)
    }

    @Test
    fun `viridis palette generates expected number of colors`() {
        val scale = scaleColorViridis()
        val colors = scale.palette(7)
        assertEquals(7, colors.size)
    }

    @Test
    fun `gradient palette generates expected number of colors`() {
        val scale = scaleColorGradient(low = "red", high = "blue")
        val colors = scale.palette(4)
        assertEquals(4, colors.size)
    }

    @Test
    fun `gradient palette generates correct colors`() {
        val scale = scaleColorGradient(low = "#ff0000", high = "#0000ff")
        val colors = scale.palette(3).map { it.lowercase() }
        assertEquals("#ff0000", colors[0])
        assertEquals("#ca0088", colors[1])  // midpoint between red and blue
        assertEquals("#0000ff", colors[2])
    }

    @Test
    fun `grey palette generates expected number of colors`() {
        val scale = scaleColorGrey()
        val colors = scale.palette(3)
        assertEquals(3, colors.size)
    }

    @Test
    fun `palette with zero colors returns empty list`() {
        val scale = scaleColorBrewer()
        val colors = scale.palette(0)
        assertEquals(emptyList(), colors)
    }
}