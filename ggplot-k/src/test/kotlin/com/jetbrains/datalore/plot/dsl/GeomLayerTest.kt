package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.Stat.density
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import org.junit.Test

class GeomLayerTest {

    @Test
    fun `geom with default stat`() {
        val l = geom_point(color = "C") { fill = "F" }

        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("color", "C")
            .geom()
            .kind(GeomKind.POINT)
            .noMapping().noParameters()
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.IDENTITY)
    }

    @Test
    fun `geom with overridden stat`() {
        val l = geom_point(color = "C", stat = density(kernel = "gaussian")) { fill = "F" }
        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("color", "C")
            .parameter("kernel", "gaussian")
            .geom()
            .kind(GeomKind.POINT)
            .noMapping().noParameters()
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .parameter("kernel", "gaussian")
            .noMapping()
    }

    @Test
    fun `geom with overridden stat, mapping`() {
        val l =
            geom_point(color = "C", stat = density({
                fill = "F1"; alpha = "A"
            }, kernel = "gaussian")) { fill = "F" }

        LayerAssert.assertThat(l)
            .aes("fill", "F")               // from layer
            .aes("alpha", "A")              // from stat
            .parameter("color", "C")            // from layer
            .parameter("kernel", "gaussian")    // from stat

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()

        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("fill", "F1")
            .aes("alpha", "A")
            .parameter("kernel", "gaussian")
    }
}