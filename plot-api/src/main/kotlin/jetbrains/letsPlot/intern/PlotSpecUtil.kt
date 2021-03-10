/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.datalore.base.json.JsonSupport
import jetbrains.letsPlot.Figure
import jetbrains.letsPlot.GGBunch

/**
 * Used in IDEA Plugin
 */
object PlotSpecUtil {
    @JvmStatic
    fun toSpecJson(fig: Figure): String {
        val spec: Map<String, Any> = when (fig) {
            is Plot -> fig.toSpec()
            is GGBunch -> fig.toSpec()
            else -> throw IllegalArgumentException("Unexpected Lets-Plot figure type: ${fig::class.simpleName}")
        }
        return JsonSupport.formatJson(spec)
    }

    @JvmStatic
    fun fromSpecJson(specJson: String): MutableMap<String, Any?> {
        return JsonSupport.parseJson(specJson)
    }
}