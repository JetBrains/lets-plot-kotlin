/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.Stat.density
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.stat.statBin
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.LayerAssert
import org.jetbrains.letsPlot.intern.StatKind
import org.junit.Test
import kotlin.test.assertFalse

class GeomLayerTest {

    @Test
    fun `geom with default stat`() {
        val l = geomPoint(color = "C") { fill = "F" }

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
        val l = geomPoint(color = "C", stat = density(kernel = "gaussian")) { fill = "F" }
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
            geomPoint(color = "C", stat = density(kernel = "gaussian") { x = "F1"; weight = "W" }) {
                x = "F"
            }

        LayerAssert.assertThat(l)
            .aes("weight", "W")               // from stat
            .parameter("color", "C")            // from layer
            .parameter("kernel", "gaussian")    // from stat

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()

        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("x", "F1")
            .parameter("kernel", "gaussian")
    }

    @Test
    fun `geom naRm serializes to na_rm`() {
        val defaultLayer = geomPoint()
        val falseLayer = geomPoint(naRm = false)
        val l = geomPoint(naRm = true)

        assertFalse(LayerAssert.assertThat(defaultLayer).parameterOptions.has("na_rm"))
        assertFalse(LayerAssert.assertThat(falseLayer).parameterOptions.has("na_rm"))
        LayerAssert.assertThat(l)
            .parameter("na_rm", true)
    }

    @Test
    fun `stat naRm serializes to na_rm`() {
        val defaultLayer = statBin()
        val falseLayer = statBin(naRm = false)
        val l = statBin(naRm = true)

        assertFalse(LayerAssert.assertThat(defaultLayer).parameterOptions.has("na_rm"))
        assertFalse(LayerAssert.assertThat(falseLayer).parameterOptions.has("na_rm"))
        LayerAssert.assertThat(l)
            .parameter("na_rm", true)
    }
}
