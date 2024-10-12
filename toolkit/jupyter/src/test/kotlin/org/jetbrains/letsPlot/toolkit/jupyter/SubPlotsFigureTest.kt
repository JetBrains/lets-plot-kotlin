package org.jetbrains.letsPlot.toolkit.jupyter

import org.junit.jupiter.api.TestInfo
import kotlin.test.Test

class SubPlotsFigureTest : JupyterTest() {

    private val grid = """
    val data1 = mapOf(
        "x" to listOf(1, 2, 3, 4),
        "y" to listOf(4, 3, 2, 1)
    )
    val plot1 = ggplot(data1) + geomPoint { x = "x"; y = "y" } + ggtitle("Plot 1")

    val data2 = mapOf(
        "x" to listOf(1, 2, 3, 4),
        "y" to listOf(1, 2, 3, 4)
    )
    val plot2 = ggplot(data2) + geomLine { x = "x"; y = "y" } + ggtitle("Plot 2")

    val grid = gggrid(listOf(plot1, plot2))
    grid
    """.trimIndent()

    @Test
    fun `compilation of SubPlotsFigure in jupyter`() = grid.checkCompilation()

    @Test
    fun `SubPlotsFigure output in jupyter`(testInfo: TestInfo) {
        assertOutput(execRendered(grid), testInfo)
    }
}