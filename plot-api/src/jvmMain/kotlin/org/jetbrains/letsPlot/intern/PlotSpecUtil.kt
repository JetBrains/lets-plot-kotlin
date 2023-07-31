/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.commons.intern.json.JsonSupport

/**
 * Used in IDEA Plugin
 */
@Suppress("unused")
object PlotSpecUtil {
    @JvmStatic
    fun toSpecJson(fig: Figure): String {
        val spec = fig.toSpec()
        return JsonSupport.formatJson(spec)
    }

    @JvmStatic
    fun fromSpecJson(specJson: String): MutableMap<String, Any?> {
        return JsonSupport.parseJson(specJson)
    }
}