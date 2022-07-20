/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.export

import java.util.*

object VersionChecker {
    private const val PROP_FILE = "letsPlotKotlinAPI/version.properties"
    private const val KEY_LETS_PLOT_VER = "lets_plot.version"
    private const val KEY_LETS_PLOT_KOTLIN_API_VER = "lets_plot_kotlin_api.version"

    private val PROP_MAP: Map<Any, Any> = run {
        val stream = VersionChecker::class.java.classLoader.getResourceAsStream(PROP_FILE)
            ?: throw IllegalStateException("Couldn't find resource \"$PROP_FILE\"")
        stream.use {
            val prop = Properties()
            prop.load(it)
            prop.toMap()
        }
    }

    val letsPlotVersion: String
        get() = PROP_MAP.getValue(KEY_LETS_PLOT_VER) as String
    val letsPlotJsVersion: String
        get() = (PROP_MAP.getValue(KEY_LETS_PLOT_VER) as String).replace("-", "") // x.y.z-rc1 -> x.y.zrc1
    val letsPlotKotlinAPIVersion: String
        get() = PROP_MAP.getValue(KEY_LETS_PLOT_KOTLIN_API_VER) as String
}