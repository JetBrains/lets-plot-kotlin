package plotDemo

import frontendApi.HtmlFrontendContext
import jetbrains.datalore.vis.demoUtils.browser.BrowserDemoUtil

class BrowserFrontendContext : HtmlFrontendContext {
    override var headHtml: String? = null

    override fun displayHtml(html: String) {
        val pageHtml = """
<html lang="en">
    <head>
        <title>Kotlin Plot Demo</title>
        ${headHtml ?: ""}
    </head>
    <body>
        $html
    </body>
</html>            
        """.trimIndent()

        BrowserDemoUtil.openInBrowser("demo/browser") { pageHtml }
    }
}