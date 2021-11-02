/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.base.json.JsonSupport
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.filterNonNullValues


// Environment variables
const val ENV_HTML_ISOLATED_FRAME = "LETS_PLOT_HTML_ISOLATED_FRAME"


internal object GlobalSettings {
    val isolatedFrameContext: Boolean get() = Env.getBool(ENV_HTML_ISOLATED_FRAME) ?: false
    private val settings: MutableMap<String, String> = mutableMapOf()

    fun set(pair: Pair<String, String>) {
        settings += pair
    }


    private const val PLOT_THEME = "plot_theme"

    fun setTheme(theme: OptionsMap?) {
        if (theme == null) {
            settings.remove(PLOT_THEME)
        } else {
            require(theme.kind == Option.Plot.THEME) {
                "Wrong options type. Expected `${Option.Plot.THEME}` but was `${theme.kind}`"
            }
            set(PLOT_THEME to JsonSupport.formatJson(theme.options))
        }
    }

    fun getTheme(): OptionsMap? {
        return settings[PLOT_THEME]?.let {
            OptionsMap(Option.Plot.THEME, JsonSupport.parseJson(it).filterNonNullValues())
        }
    }
}
