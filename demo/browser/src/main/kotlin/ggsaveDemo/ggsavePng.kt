/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package ggsaveDemo

import BrowserDemoUtil
import jetbrains.datalore.base.geometry.Vector
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle

@Suppress("DuplicatedCode", "ClassName")
object ggsavePng {
    @JvmStatic
    fun main(args: Array<String>) {
        val plotSize = Vector(500, 350)

        val data = mapOf<String, Any>(
            "cat1" to listOf("a", "a", "b", "a", "a", "a", "a", "b", "b", "b", "b"),
            "cat2" to listOf("c", "c", "d", "d", "d", "c", "c", "d", "c", "c", "d")
        )
        val p = ggplot(data) +
                geomBar {
                    x = "cat1"
                    fill = "cat2"
                } +
                ggtitle("Bar Chart") +
                ggsize(plotSize.x, plotSize.y)

        val path = BrowserDemoUtil.getOutputPath()

        val scales = listOf(1, 2)
        val DPIs = listOf(72, 144)
        val elements = ArrayList<String>()
        for ((scale, DPI) in scales.zip(DPIs)) {
            val pathname = ggsave(p, "ggsave_${scale}_$DPI.png", scale = scale, dpi = DPI, path = path)
            println(pathname)

            val html = """
                <p>Scaling factor: $scale, DPI: $DPI</p>
                <img src="$pathname" width="${plotSize.x}" height="${plotSize.y}"/>
            """.trimIndent()
            elements.add(html)
        }


        BrowserDemoUtil.openInBrowser(elements.joinToString(separator = ""))
    }
}