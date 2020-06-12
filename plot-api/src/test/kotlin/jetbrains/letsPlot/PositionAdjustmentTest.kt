/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.LayerAssert
import jetbrains.letsPlot.intern.PosKind
import jetbrains.letsPlot.Pos.dodge
import jetbrains.letsPlot.position_dodge
import jetbrains.letsPlot.geom.geom_point
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