package org.jetbrains.letsPlot.toolkit.jupyter

import org.junit.jupiter.api.TestInfo
import kotlin.test.Test

class PlotTest : JupyterTest() {

    private val plot = """
    val data = mapOf(
        "type" to listOf("X", "X", "Y", "Y", "X", "X", "Y", "X"),
        "cond" to listOf("A", "B", "A", "A", "A", "A", "A", "B")
    )

    var p = letsPlot(data)
    p += geomBar(color = "dark_green", alpha = .3) { x = "type"; fill = "cond" }
    p + ggsize(700, 350)
    """.trimIndent()

    @Test
    fun `compilation of Plot in jupyter`() = plot.checkCompilation()

    @Test
    fun `Plot output in jupyter`(testInfo: TestInfo) {
        assertOutput(execRendered(plot), testInfo)
    }
}