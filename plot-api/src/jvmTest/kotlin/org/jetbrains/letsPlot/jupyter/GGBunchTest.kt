package org.jetbrains.letsPlot.jupyter

import kotlin.test.Test

internal class GGBunchTest : JupyterTest() {

    private val bunch = """
    val data1 = mapOf(
        "x" to listOf(1, 2, 3, 4),
        "y" to listOf(4, 6, 5, 7)
    )
    val plot1 = ggplot(data1) +
            geomPoint(size = 5, color = "red", shape = 16) { x = "x"; y = "y" } +
            ggtitle("Plot 1") +
            ggsize(300, 300)
            
    val data2 = mapOf(
        "x" to listOf(1, 2, 3, 4, 5),
        "y" to listOf(1, 2, 3, 2, 1)
    )
    val plot2 = ggplot(data2) +
            geomLine(size = 2, color = "blue") { x = "x"; y = "y" } +
            ggtitle("Plot 2") +
            theme(axisTitleX = elementText(color = "blue", size = 14),
                axisTitleY = elementText(color = "blue", size = 14)) +
            ggsize(300, 300)

    val bunch = GGBunch()
        .addPlot(plot1, 0, 0)
        .addPlot(plot2, 350, 0)

    bunch
    """.trimIndent()

    @Test
    fun `compilation of GGBunch in jupyter`() = bunch.checkCompilation()

    @Test
    fun `GGBunch output in jupyter`() {
        assertOutput(execRendered(bunch))
    }
}