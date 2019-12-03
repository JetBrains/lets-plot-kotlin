/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.frontendContext

import frontendApi.FrontendContext
import frontendApi.HtmlFrontendContext
import frontendApi.RawObjectFrontendContext
import frontendApi.TextFrontendContext
import jetbrains.datalore.base.jsObject.JsObjectSupport.mapToJsObjectInitializer
import jetbrains.datalore.plot.server.config.PlotConfigServerSide
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import tmp.LetsPlotHtml

object FrontendContextUtil {
    fun display(plot: Plot, ctx: FrontendContext) {
        when (ctx) {
            is RawObjectFrontendContext -> ctx.displayObject(plot)
            is HtmlFrontendContext -> ctx.displayHtml(getStaticHtml(plot))   // TODO: `static html` is only good for demo
            is TextFrontendContext -> ctx.displayText(plot.toString())
            else -> throw IllegalArgumentException("Unsupported frontend context ${ctx::class.simpleName}")
        }
    }

    // Currently used as API method
    // TODO: private
    // TODO: move to Frontend Context (re-design needed)
    fun getHtml(plot: Plot): String {
        val plotSpec = PlotConfigServerSide.processTransform(plot.toSpec())
        val plotSpecJs = mapToJsObjectInitializer(plotSpec)
        return LetsPlotHtml.getDynamicScriptPlotDisplayHtml(plotSpecJs)
    }

    // TODO: move to Frontend Context (re-design needed)
    private fun getStaticHtml(plot: Plot): String {
        val plotSpec = PlotConfigServerSide.processTransform(plot.toSpec())
        val plotSpecJs = mapToJsObjectInitializer(plotSpec)
        return LetsPlotHtml.getStaticScriptPlotDisplayHtml(plotSpecJs)
    }
}