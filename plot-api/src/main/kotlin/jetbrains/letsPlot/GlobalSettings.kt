package jetbrains.letsPlot

import frontendApi.FrontendContext
import frontendApi.HtmlFrontendContext
import frontendApi.SimpleTextFrontendContext
import tmp.LetsPlotHtml

object GlobalSettings {
    var frontendContext: FrontendContext = SimpleTextFrontendContext()
        set(value) {
            field = value
            if (value is HtmlFrontendContext) {
                value.headHtml = LetsPlotHtml.getStaticScriptLoadingHtml()
            }
        }
}