package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.Geom.point
import com.jetbrains.datalore.plot.dsl.Stat.density
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.stat.stat_density
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
                    group = "stat G"
                    linetype = "stat L"
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
            .aes("linetype", "stat L")
            .parameter("color", "layer C")
            .parameter("fill", "layer F")
            .parameter("kernel", "gaussian")
            .stat().kind(StatKind.DENSITY)
            .aes("x", "stat X")
            .aes("group", "stat G")
            .aes("linetype", "stat L")
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
        val geomLayer = geom_point(
            color = "C",
            stat = density(
                { x = "X"; linetype = "L" },
                kernel = "gaussian"
            )
        ) { fill = "F" }

        var statLayer: stat_density
        run {
            statLayer = stat_density(
                color = "C",
                geom = point({ fill = "F" }),
                kernel = "gaussian"
            ) { x = "X"; linetype = "L" }

            assertEquals(geomLayer.mapping.map, statLayer.mapping.map)
            assertEquals(geomLayer.parameters.map, statLayer.parameters.map)
        }

        run {
            statLayer = stat_density(
                geom = point({ fill = "F" }, color = "C"),
                kernel = "gaussian"
            ) { x = "X"; linetype = "L" }

            assertEquals(geomLayer.mapping.map, statLayer.mapping.map)
            assertEquals(geomLayer.parameters.map, statLayer.parameters.map)
        }
    }
}