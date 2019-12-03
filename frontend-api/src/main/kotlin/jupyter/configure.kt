/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

// TODO: fix package
package jetbrains.datalore.jupyter

import tmp.LetsPlotHtml

// Currently used as API method
// TODO: move to Frontend Context (re-design needed)
fun configureScript(): String {
    return LetsPlotHtml.getDynamicScriptLoadingHtml(false)
}