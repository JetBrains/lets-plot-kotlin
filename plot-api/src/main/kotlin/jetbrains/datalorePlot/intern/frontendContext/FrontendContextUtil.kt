package jetbrains.datalorePlot.intern.frontendContext

import frontendApi.FrontendContext
import frontendApi.HtmlFrontendContext
import frontendApi.RawObjectFrontendContext
import frontendApi.TextFrontendContext
import jetbrains.datalorePlot.intern.Plot

object FrontendContextUtil {
    fun display(plot: Plot, ctx: FrontendContext) {
        when (ctx) {
            is TextFrontendContext -> ctx.displayText(plot.toString())
            is RawObjectFrontendContext -> ctx.displayObject(plot)
            is HtmlFrontendContext -> TODO()
            else -> throw IllegalArgumentException("Unsupported frontend context ${ctx::class.simpleName}")
        }
    }
}