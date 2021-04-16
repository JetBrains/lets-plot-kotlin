/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.Geom.point
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.LayerAssert
import jetbrains.letsPlot.intern.StatKind
import jetbrains.letsPlot.stat.statDensity
import org.junit.Test

class StatLayerTest {

    @Test
    fun `stat with default geom`() {
        val l = statDensity(color = "C") { fill = "F" }

        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("color", "C")
            .stat()
            .kind(StatKind.DENSITY)
            .noMapping().noParameters()

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.DENSITY)
            .noMapping().noParameters()
    }

    @Test
    fun `stat with overridden geom`() {
        val l = statDensity(mapping = { x = "X"; fill = "F" }, color = "C", geom = point(alpha = 0.5))

        LayerAssert.assertThat(l)
            .aes("x", "X")
            .aes("fill", "F")
            .parameter("color", "C")
            .parameter("alpha", 0.5)
            .stat()
            .kind(StatKind.DENSITY)
            .noMapping().noParameters()

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()
            .parameter("alpha", 0.5)
    }

    @Test
    fun `stat with parameter`() {
        val l = statDensity(kernel = "gaussian", color = "C") { fill = "F" }
        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("kernel", "gaussian")
            .parameter("color", "C")
            .stat()
            .kind(StatKind.DENSITY)
            .noMapping().noParameters()
    }
}