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

class BrowserDemoFrontendContext : FrontendContext {
    private val file = BrowserDemoUtil.createTemporaryFile(DEMO_PROJECT_RELATIVE_PATH)

    init {
        FileWriter(file).use {
            it.write(LetsPlotHtml.getStaticScriptLoadingHtml())
        }
    }

    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        val plotSpec = PlotConfigServerSide.processTransform(plotSpecRaw)
        val plotSpecJs = JsObjectSupport.mapToJsObjectInitializer(plotSpec)
        val html = LetsPlotHtml.getStaticScriptPlotDisplayHtml(plotSpecJs)

        FileWriter(file, true).use {
            it.write(html)
        }
    }

    fun showAll() {
        BrowserDemoUtil.openInBrowser(file)
    }

    companion object {
        const val DEMO_PROJECT_RELATIVE_PATH = "demo/browser"
    }
}