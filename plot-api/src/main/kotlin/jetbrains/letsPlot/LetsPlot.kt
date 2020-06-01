/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

object LetsPlot {
    var frontendContext: FrontendContext = object : FrontendContext {
        override fun display(plotSpecRaw: MutableMap<String, Any>) {
            throw IllegalStateException("Frontend context is not defined")
        }
    }

    var apiVersion: String = "Unknown"
    var libraryVersion: String = "Unknown"

    fun getInfo() = "Lets-Plot library version: $libraryVersion. Kotlin API version: $apiVersion."
}