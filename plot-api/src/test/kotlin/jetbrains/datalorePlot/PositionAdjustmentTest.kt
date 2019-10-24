package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.LayerAssert
import jetbrains.datalorePlot.intern.PosKind
import jetbrains.datalorePlot.Pos.dodge
import jetbrains.datalorePlot.Pos.position_dodge
import jetbrains.datalorePlot.geom.geom_point
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