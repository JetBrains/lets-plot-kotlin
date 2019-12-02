// TODO: fix package
package jetbrains.datalore.jupyter

import tmp.LetsPlotHtml

// Currently used as API method
// TODO: move to Frontend Context (re-design needed)
fun configureScript(): String {
    return LetsPlotHtml.getDynamicScriptLoadingHtml(false)
}