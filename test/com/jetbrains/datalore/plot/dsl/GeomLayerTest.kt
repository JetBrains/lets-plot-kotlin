package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.stat.density
import org.junit.Test

class GeomLayerTest {

    @Test
    fun `geom with default stat`() {
        val l = geom_point({ fill = "F" }, color = "C")
        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .aes("fill", "F")
            .constant("color", "C")
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.IDENTITY)
    }

    @Test
    fun `geom with overridden stat`() {
        val l = geom_point({ fill = "F" }, color = "C", stat = density(kernel = "gaussian"))
        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .aes("fill", "F")
            .constant("color", "C")
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .parameter("kernel", "gaussian")
    }

    @Test
    fun `geom with overridden stat, mapping`() {
        val l =
            geom_point({ fill = "F" }, color = "C", stat = density({ fill = "F1"; alpha = "A" }, kernel = "gaussian"))
        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .aes("fill", "F")
            .constant("color", "C")
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("fill", "F1")
            .aes("alpha", "A")
            .parameter("kernel", "gaussian")

        // When merged, `stat` mapping has precedence over `geom` mapping
        LayerAssert.assertThat(l)
            .aes("fill", "F1")
            .aes("alpha", "A")
    }
}