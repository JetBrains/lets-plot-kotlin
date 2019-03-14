package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.PlotAssert.Companion.assertThat
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.stat.density
import org.junit.Test

class PlotTest {

    @Test
    fun `empty plot`() {
        var p = ggplot()
        assertThat(p).features().length(0)

        p = ggplot {}
        assertThat(p).features().length(0)
    }

    @Test
    fun `empty plot with mapping`() {
        var p = ggplot(mapping = { x = "X"; y = "Y" })
        assertThat(p).features().length(0)
        assertThat(p).mapping()
            .aes("x", "X")
            .aes("y", "Y")

        p = ggplot { alpha = "A"; group = "G" }
        assertThat(p).features().length(0)
        assertThat(p).mapping()
            .aes("alpha", "A")
            .aes("group", "G")

        p = ggplot { color = "C"; fill = "F" }
        assertThat(p).features().length(0)
        assertThat(p).mapping()
            .aes("color", "C")
            .aes("fill", "F")
    }

    @Test
    fun `plot with layer and mapping`() {
        val p = ggplot() + geom_point({ x = "X"; color = "C" })
        assertThat(p).layers()
            .length(1)
            .get(0).mapping()
            .aes("x", "X")
            .aes("color", "C")

        // same mappings are accessible via `geom`
        assertThat(p).layers()
            .length(1)
            .get(0).geom()
            .kind(GeomKind.POINT)
            .mapping()
            .aes("x", "X")
            .aes("color", "C")
    }

    @Test
    fun `plot with layer and constants`() {
        val p = ggplot() + geom_point(x = 1, y = 2, color = "C")
        assertThat(p).layers()
            .length(1)
            .get(0).noMapping()
            .geom().noMapping()
            .kind(GeomKind.POINT)
            .constant("x", 1)
            .constant("y", 2)
            .constant("color", "C")
    }

    @Test
    fun `plot with layer, mapping and constants`() {
        val p = ggplot() + geom_point(
            { x = "X"; fill = "F" },
            x = 1,
            y = 2,
            color = "C"
        )
        assertThat(p).layers()
            .length(1)
            .get(0)
            .geom()
            .kind(GeomKind.POINT)
            .constant("x", 1)
            .constant("y", 2)
            .constant("color", "C")
            .mapping()
            .aes("x", "X")
            .aes("fill", "F")
    }

    @Test
    fun `plot with layer and group mapping`() {
        val p = ggplot() + geom_point({ x = "X"; group = "G" }, color = "C")
        assertThat(p).layers()
            .length(1)
            .get(0)
            .geom()
            .kind(GeomKind.POINT)
            .constant("color", "C")
            .mapping()
            .aes("x", "X")
            .aes("group", "G")
    }

    @Test
    fun `plot with point geom and density stat`() {
        val p = ggplot() + geom_point({ group = "G" }, stat = density({ x = "X" }, kernel = "gaussian"), color = "C")
        assertThat(p).layers()
            .length(1)
            .get(0).mapping()
            .aes("group", "G")
            .aes("x", "X")
    }
}