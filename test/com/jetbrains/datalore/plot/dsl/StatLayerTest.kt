package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.geom.point
import com.jetbrains.datalore.plot.dsl.stat.stat_density
import org.junit.Test

class StatLayerTest {

    @Test
    fun `stat with default geom`() {
        val l = stat_density({ fill = "F" }, color = "C")

        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("fill", "F")
            .parameter("color", "C")

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.AREA)
            .noMapping()
    }

    @Test
    fun `stat with overridden geom`() {
        val l = stat_density({ x = "X"; fill = "F" }, color = "C", geom = point(alpha = 0.5))

        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("fill", "F")
            .parameter("color", "C")

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()
    }

    @Test
    fun `stat with parameter`() {
        val l = stat_density({ fill = "F" }, kernel = "gaussian", color = "C")
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("fill", "F")
            .parameter("kernel", "gaussian")
            .parameter("color", "C")
    }

//    @Test
//    fun `geom with overridden stat, mapping`() {
//        val l =
//            geom_point({ fill = "F" }, color = "C", stat = density({ fill = "F1"; alpha = "A" }, kernel = "gaussian"))
//        LayerAssert.assertThat(l)
//            .geom()
//            .kind(GeomKind.POINT)
//            .aes("fill", "F")
//            .constant("color", "C")
//        LayerAssert.assertThat(l)
//            .stat()
//            .kind(StatKind.DENSITY)
//            .aes("fill", "F1")
//            .aes("alpha", "A")
//            .parameter("kernel", "gaussian")
//
//        // When merged, `stat` mapping has precedence over `geom` mapping
//        LayerAssert.assertThat(l)
//            .aes("fill", "F1")
//            .aes("alpha", "A")
//    }
}