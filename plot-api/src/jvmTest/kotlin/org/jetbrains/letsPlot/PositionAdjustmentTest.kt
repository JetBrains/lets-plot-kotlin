/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.Pos.dodge
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.LayerAssert
import org.jetbrains.letsPlot.intern.PosKind
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Assert.assertEquals
import org.junit.Test

class PositionAdjustmentTest {

    @Test
    fun `layer with default pos`() {
        val l = geomPoint()

        LayerAssert.assertThat(l)
            .position(PosKind.IDENTITY)
            .noParameters()
    }

    @Test
    fun `layer with overridden pos`() {
        val l = geomPoint(position = dodge)

        LayerAssert.assertThat(l)
            .position(PosKind.DODGE)
            .noParameters()
    }

    @Test
    fun `layer with overridden pos and parameters`() {
        val l = geomPoint(position = positionDodge(width = 10))

        LayerAssert.assertThat(l)
            .position(PosKind.DODGE)
            .parameter("width", 10.0)


        val expectedSpec = HashMap(geomPoint().toSpec())
        expectedSpec[Option.Layer.POS] = mapOf(
            Option.Meta.KIND to Option.Meta.Kind.POS,
            "name" to "dodge",
            "width" to 10.0
        )
        assertEquals(
            expectedSpec,
            l.toSpec()
        )
    }
}