/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.settings


// Environment variables
const val ENV_HTML_ISOLATED_FRAME = "LETS_PLOT_HTML_ISOLATED_FRAME"


internal object GlobalSettings {
    val isolatedFrameContext: Boolean get() = Env.getBool(ENV_HTML_ISOLATED_FRAME) ?: false
}
