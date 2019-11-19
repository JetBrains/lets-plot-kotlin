package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.PlotAssert.Companion.assertThat
import jetbrains.datalorePlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PlotSizeTest {

    @Test
    fun `check plot option`() {
        val p = ggplot() + ggsize(5, 10)
        assertThat(p).features().length(1)

        val spec = p.toSpec()
        assertEquals(
            mapOf(
                "kind" to "plot",
                "mapping" to emptyMap<Any, Any>(),
                "layers" to emptyList<Any>(),
                "scales" to emptyList<Any>(),
                "ggsize" to mapOf("height" to 10, "width" to 5)
            ),
            spec
        )
    }
}