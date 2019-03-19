package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.PosKind
import com.jetbrains.datalore.plot.dsl.Pos.dodge
import com.jetbrains.datalore.plot.dsl.Pos.position_dodge
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import org.junit.Test

class PositionAdjustmentTest {

    @Test
    fun `layer with default pos`() {
        val l = geom_point()

        LayerAssert.assertThat(l)
            .position(PosKind.IDENTITY)
            .noParameters()
    }

    @Test
    fun `layer with overridden pos`() {
        val l = geom_point(position = dodge)

        LayerAssert.assertThat(l)
            .position(PosKind.DODGE)
            .noParameters()
    }

    @Test
    fun `layer with overridden pos and parameters`() {
        val l = geom_point(position = position_dodge(width = 10))

        LayerAssert.assertThat(l)
            .position(PosKind.DODGE)
            .parameter("width", 10)
    }
}