/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.frontend.DefaultSwingBatikFrontendContext
import jetbrains.letsPlot.frontend.DefaultSwingJfxFrontendContext
import jetbrains.letsPlot.frontend.NotebookFrontendContext

// Environment variables
const val ENV_HTML_ISOLATED_FRAME = "LETS_PLOT_HTML_ISOLATED_FRAME"

object LetsPlot {
    var frontendContext: FrontendContext = initDefaultFrontendContext()

    @Suppress("MemberVisibilityCanBePrivate")
    var apiVersion: String = "Unknown"

    @Suppress("unused")
    fun getInfo() = "Lets-Plot Kotlin API v.$apiVersion. Frontend: ${frontendContext.getInfo()}"

    @Suppress("unused")
    fun setupNotebook(
        jsVersion: String,
        isolatedFrame: Boolean?,
        htmlRenderer: (String) -> Unit
    ): NotebookFrontendContext {

        val isolatedFrameContext: Boolean = isolatedFrame ?: getBooleanFromEnv(ENV_HTML_ISOLATED_FRAME)
        this.frontendContext = NotebookFrontendContext(jsVersion, isolatedFrameContext, htmlRenderer)
        return frontendContext as NotebookFrontendContext
    }

    private fun getBooleanFromEnv(@Suppress("SameParameterValue") name: String): Boolean {
        val value = System.getenv(name)
        return when {
            value.isNullOrBlank() -> false
            value.trim().toLowerCase() in listOf("true", "1", "t", "y", "yes") -> true
            value.trim().toLowerCase() in listOf("false", "0", "f", "n", "no") -> false
            else -> {
                throw IllegalArgumentException("Can't convert str to boolean : [$name] : $value")
            }
        }
    }

    private fun initDefaultFrontendContext(): FrontendContext {
        return DefaultSwingBatikFrontendContext.tryCreate()
            ?: DefaultSwingJfxFrontendContext.tryCreate()
            ?: object : FrontendContext {
                override fun display(plotSpecRaw: MutableMap<String, Any>) {
                    throw IllegalStateException(
                        """
                            
                            The frontend context is not defined.
                            To define the frontend context please select one of the following options:
                            a. Add "lets-plot-batik-<version>.jar" to your classpath.   
                            b. Add "lets-plot-jfx-<version>.jar" to your classpath.   
                            c. Specify the frontend context explicitly: "LetsPlot.frontendContext = ..."
                               
                            """.trimIndent()
                    )
                }
            }
    }
}