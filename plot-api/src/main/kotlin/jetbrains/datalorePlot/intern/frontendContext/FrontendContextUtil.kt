package jetbrains.datalorePlot.intern.frontendContext

import frontendApi.FrontendContext
import frontendApi.HtmlFrontendContext
import frontendApi.RawObjectFrontendContext
import frontendApi.TextFrontendContext
import jetbrains.datalore.base.jsObject.JsObjectSupport.mapToJsObjectInitializer
import jetbrains.datalore.plot.server.config.PlotConfigServerSide
import jetbrains.datalorePlot.intern.Plot
import jetbrains.datalorePlot.intern.toSpec

object FrontendContextUtil {
    fun display(plot: Plot, ctx: FrontendContext) {
        when (ctx) {
            is RawObjectFrontendContext -> ctx.displayObject(plot)
            is HtmlFrontendContext -> displayHtml(plot, ctx)
            is TextFrontendContext -> ctx.displayText(plot.toString())
            else -> throw IllegalArgumentException("Unsupported frontend context ${ctx::class.simpleName}")
        }
    }

    fun getHtml(plot: Plot) : String {
        val plotSpec = PlotConfigServerSide.processTransform(plot.toSpec())
        val plotOutputId = "plot_output_${randomStr()}"
        val plotSpecJs = mapToJsObjectInitializer(plotSpec)

        return """
            <div id="$plotOutputId"></div>
            <script type="text/javascript">
                var plotSpec=$plotSpecJs;

                var plotContainer = document.getElementById("$plotOutputId");
                DatalorePlot.buildPlotFromProcessedSpecs(plotSpec, -1, -1, plotContainer);
            </script>
        """
    }

    private fun displayHtml(plot: Plot, ctx: HtmlFrontendContext) {
        ctx.displayHtml(getHtml(plot))
    }

    private fun randomStr(): String {
        val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(6) { alphabet.random() }.joinToString("")
    }
}