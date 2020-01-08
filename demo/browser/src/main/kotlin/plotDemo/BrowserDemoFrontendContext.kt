/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotDemo

import jetbrains.datalore.base.jsObject.JsObjectSupport
import jetbrains.datalore.plot.server.config.PlotConfigServerSide
import jetbrains.letsPlot.FrontendContext
import tmp.LetsPlotHtml
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
                |   ${LetsPlotHtml.getStaticScriptLoadingHtml()}
                |   
                |</head>
                |<body>
                |
            """.trimMargin()
            )

            // append JS with plot data and a call to the build function.
            for (plotSpec in plotSpecs) {
                @Suppress("NAME_SHADOWING")
                val plotSpec = PlotConfigServerSide.processTransform(plotSpec)
                val plotSpecJs = JsObjectSupport.mapToJsObjectInitializer(plotSpec)
                val html = LetsPlotHtml.getStaticScriptPlotDisplayHtml(plotSpecJs)

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