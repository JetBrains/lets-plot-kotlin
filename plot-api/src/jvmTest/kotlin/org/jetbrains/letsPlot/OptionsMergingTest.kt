/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import junit.framework.TestCase.assertEquals
import org.jetbrains.letsPlot.Geom.point
import org.jetbrains.letsPlot.Stat.density
import org.jetbrains.letsPlot.geom.geomArea
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.LayerAssert
import org.jetbrains.letsPlot.intern.StatKind
import org.jetbrains.letsPlot.stat.statDensity
import org.junit.Test

class OptionsMergingTest {

    @Test
    fun `layer options precedence over geom`() {
        val l = geomPoint(
            color = "layer C", fill = "layer F",
            stat = density(kernel = "gaussian") {
                x = "stat X"
                weight = "stat W"
            }
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
        val l = statDensity(
            color = "layer C", fill = "layer F",
            geom = point {
                x = "stat X"
                group = "stat G"
            },
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
        val geomLayer = geomArea(
            color = "C",
            stat = density(kernel = "gaussian") { x = "X" }
        ) { fill = "F" }

        var statLayer: statDensity
        run {
            statLayer = statDensity(
                color = "C",
                geom = point { fill = "F" },
                kernel = "gaussian"
            ) { x = "X" }

            assertEquals(geomLayer.mapping.map, statLayer.mapping.map)
            assertEquals(geomLayer.parameters.map, statLayer.parameters.map)
        }

        run {
            statLayer = statDensity(
                geom = point(color = "C") { fill = "F" },
                kernel = "gaussian"
            ) { x = "X" }

            assertEquals(geomLayer.mapping.map, statLayer.mapping.map)
            assertEquals(geomLayer.parameters.map, statLayer.parameters.map)
        }
    }
}