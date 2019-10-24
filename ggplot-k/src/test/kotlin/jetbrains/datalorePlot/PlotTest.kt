package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.GeomKind
import jetbrains.datalorePlot.intern.PlotAssert.Companion.assertThat
import jetbrains.datalorePlot.geom.geom_point
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
        assertThat(p)
            .aes("x", "X")
            .aes("y", "Y")

        val data = emptyMap<String, List<Any>>()
        p = ggplot(data) { alpha = "A"; group = "G" }
        assertThat(p).features().length(0)
        assertThat(p)
            .aes("alpha", "A")
            .aes("group", "G")

        p = ggplot { color = "C"; fill = "F" }
        assertThat(p).features().length(0)
        assertThat(p)
            .aes("color", "C")
            .aes("fill", "F")
    }

    @Test
    fun `plot with layer and mapping`() {
        val p = ggplot() + geom_point { x = "X"; color = "C" }
        assertThat(p).layers()
            .length(1)
            .get(0)
            .aes("x", "X")
            .aes("color", "C")
            .geom()
            .kind(GeomKind.POINT)
    }

    @Test
    fun `plot with layer and constants`() {
        val p = ggplot() + geom_point(x = 1.0, y = 2.0, color = "C")
        assertThat(p).layers()
            .length(1)
            .get(0)
            .noMapping()
            .parameter("x", 1.0)
            .parameter("y", 2.0)
            .parameter("color", "C")
            .geom()
            .kind(GeomKind.POINT)
            .noMapping().noParameters()
    }

    @Test
    fun `plot with layer, mapping and constants`() {
        val p = ggplot() + geom_point(
            mapping = { x = "X"; fill = "F" },
            x = 1.0,
            y = 2.0,
            color = "C"
        )
        assertThat(p).layers()
            .length(1)
            .get(0)
            .aes("x", "X")
            .aes("fill", "F")
            .parameter("x", 1.0)
            .parameter("y", 2.0)
            .parameter("color", "C")
            .geom()
            .kind(GeomKind.POINT)
    }

    @Test
    fun `plot with layer and group mapping`() {
        val p = ggplot() + geom_point(mapping = { x = "X"; group = "G" }, color = "C")
        assertThat(p).layers()
            .length(1)
            .get(0)
            .aes("x", "X")
            .aes("group", "G")
            .parameter("color", "C")
            .geom()
            .kind(GeomKind.POINT)
    }

    @Test
    fun tmp() {

    }
}