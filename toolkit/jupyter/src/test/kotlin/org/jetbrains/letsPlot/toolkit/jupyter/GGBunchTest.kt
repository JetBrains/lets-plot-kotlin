package org.jetbrains.letsPlot.toolkit.jupyter

import org.junit.jupiter.api.TestInfo
import kotlin.test.Ignore
import kotlin.test.Test

class GGBunchTest : JupyterTest() {

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

    val bunch = ggbunch(
        listOf(plot1, plot2),
        regions = listOf(
            listOf(0, 0, 0.5, 1), // Plot 1: x=0, y=0, width=0.5, height=1
            listOf(0.5, 0, 0.5, 1) // Plot 2: x=0.5, y=0, width=0.5, height=1
        )
    )

    bunch
    """.trimIndent()

    @Test
    fun `compilation of ggbunch in jupyter`() = bunch.checkCompilation()

    @Ignore
    @Test
    fun `ggbunch output in jupyter`(testInfo: TestInfo) {
        assertOutput(execRendered(bunch), testInfo)
    }
}