package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import org.junit.Test

class GeomLayerTest {

    @Test
    fun `geom with default stat`() {
        val l = geom_point({ fill = "F" }, color = "C")
        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .constant("color", "C")
            .mapping()
            .aes("fill", "F")
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.IDENTITY)
    }
}