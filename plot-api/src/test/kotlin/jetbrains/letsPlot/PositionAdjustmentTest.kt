/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.Pos.dodge
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.intern.LayerAssert
import jetbrains.letsPlot.intern.PosKind
import jetbrains.letsPlot.intern.toSpec
import org.junit.Assert.assertEquals
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


        val expectedSpec = HashMap(geom_point().toSpec())
        expectedSpec[Option.Layer.POS] = mapOf(
            Option.Meta.KIND to Option.Meta.Kind.POS,
            "name" to "dodge",
            "width" to 10
        )
        assertEquals(
            expectedSpec,
            l.toSpec()
        )
    }
}