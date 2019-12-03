/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotDemo

import frontendApi.HtmlFrontendContext

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