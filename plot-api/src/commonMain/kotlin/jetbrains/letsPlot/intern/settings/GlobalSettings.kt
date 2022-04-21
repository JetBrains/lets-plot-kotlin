/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.settings

import jetbrains.datalore.base.json.JsonSupport.parseJson
import jetbrains.datalore.plot.config.Option.Plot.THEME
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.filterNonNullValues


// Environment variables
const val ENV_HTML_ISOLATED_FRAME = "LETS_PLOT_HTML_ISOLATED_FRAME"
const val ENV_PLOT_THEME = "LETS_PLOT_PLOT_THEME"


internal object GlobalSettings {
    val isolatedFrameContext: Boolean get() = Env.getBool(ENV_HTML_ISOLATED_FRAME) ?: false

    var theme: OptionsMap? = Env.get(ENV_PLOT_THEME)?.let { OptionsMap(THEME, parseJson(it).filterNonNullValues()) }
        set(value) {
            require(value == null || value.kind == THEME) {
                "Wrong options type. Expected `$THEME` but was `${value!!.kind}`"
            }

            field = value
        }
}
