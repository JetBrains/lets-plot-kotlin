/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

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