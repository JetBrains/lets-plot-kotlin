package frontendApi

interface HtmlFrontendContext : FrontendContext {
    var headHtml: String?

    fun displayHtml(html: String)
}