/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.Geom.point
import jetbrains.letsPlot.Stat.density
import jetbrains.letsPlot.geom.geom_area
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.LayerAssert
import jetbrains.letsPlot.intern.StatKind
import jetbrains.letsPlot.stat.stat_density
import junit.framework.TestCase.assertEquals
import org.junit.Test

class OptionsMergingTest {

    @Test
    fun `layer options precedence over geom`() {
        val l = geom_point(
            color = "layer C", fill = "layer F",
            stat = density(
                {
                    x = "stat X"
                    weight = "stat W"
                },
                kernel = "gaussian"
            )
        ) {
            x = "layer X"
            y = "layer Y"
            group = "layer G"
        }

        LayerAssert.assertThat(l)
            .aes("x", "layer X")
            .aes("y", "layer Y")
            .aes("group", "layer G")
            .aes("weight", "stat W")
            .parameter("color", "layer C")
            .parameter("fill", "layer F")
            .parameter("kernel", "gaussian")
            .stat().kind(StatKind.DENSITY)
            .aes("x", "stat X")
            .parameter("kernel", "gaussian")
    }

    @Test
    fun `layer options precedence over stat`() {
        val l = stat_density(
            color = "layer C", fill = "layer F",
            geom = point({
                x = "stat X"
                group = "stat G"
            }),
            kernel = "gaussian"
        ) {
            x = "layer X"
            y = "layer Y"
            group = "layer G"
            linetype = "layer L"
        }

        LayerAssert.assertThat(l)
            .aes("x", "layer X")
            .aes("y", "layer Y")
            .aes("group", "layer G")
            .aes("linetype", "layer L")
            .parameter("color", "layer C")
            .parameter("fill", "layer F")
            .parameter("kernel", "gaussian")
            .geom().kind(GeomKind.POINT)
            .aes("x", "stat X")
            .aes("group", "stat G")
    }

    @Test
    fun `geom and stat layer equivalence`() {
        val geomLayer = geom_area(
            color = "C",
            stat = density(
                { x = "X" },
                kernel = "gaussian"
            )
        ) { fill = "F" }

        var statLayer: stat_density
        run {
            statLayer = stat_density(
                color = "C",
                geom = point({ fill = "F" }),
                kernel = "gaussian"
            ) { x = "X" }

            assertEquals(geomLayer.mapping.map, statLayer.mapping.map)
            assertEquals(geomLayer.parameters.map, statLayer.parameters.map)
        }

        run {
            statLayer = stat_density(
                geom = point({ fill = "F" }, color = "C"),
                kernel = "gaussian"
            ) { x = "X" }

            assertEquals(geomLayer.mapping.map, statLayer.mapping.map)
            assertEquals(geomLayer.parameters.map, statLayer.parameters.map)
        }
    }
}