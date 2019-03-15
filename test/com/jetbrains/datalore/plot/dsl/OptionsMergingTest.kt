package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.geom.point
import com.jetbrains.datalore.plot.dsl.stat.density
import com.jetbrains.datalore.plot.dsl.stat.stat_density
import org.junit.Test

class OptionsMergingTest {

    @Test
    fun `geom layer options precedence`() {
        val l = geom_point(
            {
                x = "layer X"
                y = "layer Y"
                group = "layer G"
            }, color = "layer C", fill = "layer F", stat = density(
                {
                    x = "stat X"
                    group = "stat G"
                    linetype = "stat L"
                }, kernel = "gaussian"
            )
        )

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
    fun `stat layer options precedence`() {
        val l = stat_density(
            {
                x = "layer X"
                y = "layer Y"
                group = "layer G"
                linetype = "stat L"
            }, color = "layer C", fill = "layer F", geom = point(
                {
                    x = "stat X"
                    group = "stat G"
                }
            ), kernel = "gaussian"
        )

        LayerAssert.assertThat(l)
            .aes("x", "layer X")
            .aes("y", "layer Y")
            .aes("group", "layer G")
            .aes("linetype", "stat L")
            .parameter("color", "layer C")
            .parameter("fill", "layer F")
            .parameter("kernel", "gaussian")
            .geom().kind(GeomKind.POINT)
            .aes("x", "stat X")
            .aes("group", "stat G")
    }
}