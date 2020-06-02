/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.frontend.NotebookFrontendContext

// Environment variables
const val ENV_HTML_ISOLATED_FRAME = "LETS_PLOT_HTML_ISOLATED_FRAME"

object LetsPlot {
    var frontendContext: FrontendContext = object : FrontendContext {
        override fun display(plotSpecRaw: MutableMap<String, Any>) {
            throw IllegalStateException("Frontend context is not defined")
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    var apiVersion: String = "Unknown"

    @Suppress("unused")
    fun getInfo() = "Lets-Plot Kotlin API v.$apiVersion. Frontend: ${frontendContext.getInfo()}"

    @Suppress("unused")
    fun setupNotebook(
        libraryVersion: String,
        isolatedFrame: Boolean?,
        htmlRenderer: (String) -> Unit
    ): NotebookFrontendContext {

        val isolatedFrameContext: Boolean = isolatedFrame ?: getBooleanFromEnv(ENV_HTML_ISOLATED_FRAME)
        this.frontendContext = NotebookFrontendContext(libraryVersion, isolatedFrameContext, htmlRenderer)
        return frontendContext as NotebookFrontendContext
    }

    private fun getBooleanFromEnv(@Suppress("SameParameterValue") name: String): Boolean {
        val value = System.getenv(name)
        return when {
            value == null -> {
                false
            }
            value.trim().toLowerCase() in listOf("true", "1", "t", "y", "yes") -> {
                true
            }
            value.trim().toLowerCase() in listOf("false", "0", "f", "n", "no") -> {
                false
            }
            else -> {
                throw IllegalArgumentException("Can't convert str to boolean : [$name] : $value")
            }
        }
    }
}