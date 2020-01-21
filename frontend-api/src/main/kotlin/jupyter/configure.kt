/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

// TODO: fix package
package jetbrains.datalore.jupyter

import jetbrains.datalore.plot.PlotHtmlHelper

private const val VERSION = "1.0.0"

// Currently used as API method
// TODO: move to Frontend Context (re-design needed)
fun configureScript(): String {
    return PlotHtmlHelper.getDynamicConfigureHtml(PlotHtmlHelper.scriptUrl(VERSION), false)
}