/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo

import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.datalore.plot.PlotHtmlHelper.scriptUrl
import jetbrains.letsPlot.FrontendContext
import java.io.FileWriter

class BrowserDemoFrontendContext(private val title: String) : FrontendContext {
    private val plotSpecs = ArrayList<MutableMap<String, Any>>()

    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        plotSpecs.add(plotSpecRaw)
    }

    fun showAll() {
        val file = BrowserDemoUtil.createTemporaryFile(DEMO_PROJECT_RELATIVE_PATH)

        FileWriter(file).use {
            it.write(
                """
                |<head>
                |   <title>$title</title>
                |
                |   ${PlotHtmlHelper.getStaticConfigureHtml(scriptUrl("1.1.dev2"))}
                |   
                |</head>
                |<body>
                |
            """.trimMargin()
            )

            // For each `plot spec` append html/js block responsible for building the plot
            for (plotSpec in plotSpecs) {
                val html = PlotHtmlHelper.getStaticDisplayHtmlForRawSpec(plotSpec)

                it.write(html)
                it.write("\n")
            }

            it.write("</body>")
        }

        BrowserDemoUtil.openInBrowser(file)
    }

    companion object {
        const val DEMO_PROJECT_RELATIVE_PATH = "demo/browser"
    }
}