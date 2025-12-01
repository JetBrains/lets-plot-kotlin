package org.jetbrains.letsPlot.toolkit.jupyter

import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.Notebook
import org.jetbrains.kotlinx.jupyter.api.declare
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.LetsPlot
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper.scriptUrl
import org.jetbrains.letsPlot.export.VersionChecker
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext

@Suppress("unused")
internal class Integration(private val notebook: Notebook, options: MutableMap<String, String?>) :
    JupyterIntegration() {

    // used by kandy-lets-plot
    internal val config = JupyterConfig()
    private lateinit var frontendContext: NotebookFrontendContext

    companion object {
        private const val JS = "js"      // Classic Web output: HTML+JS
        private const val KTNB = "ktnb"  // Kotlin Notebook Swing-based rendering
        private const val SVG = "svg"    // Static SVG output
        private const val PNG = "png"    // Static PNG output

        private const val DEFAULT_OUTPUT = "$JS, $KTNB, $SVG"
    }

    // Take integration options from descriptor by default;
    // If used via Kotlin Notebook plugin as a dependency,
    // provide defaults (versions from `VersionChecker`
    // and empty `isolatedFrame`)
    private val lpkVersion = options["v"] ?: VersionChecker.letsPlotKotlinAPIVersion
    private val lpJsVersion = VersionChecker.letsPlotJsVersion
    private val isolatedFrame = options["isolatedFrame"] ?: ""

    // Output options
    private val addWebOutput: Boolean
    private val addKTNBOutput: Boolean
    private val addStaticSvg: Boolean
    private val addStaticPng: Boolean

    init {
        val outputOption = options["output"] ?: DEFAULT_OUTPUT
        if (outputOption.isEmpty()) {
            throw IllegalArgumentException(
                "Output option cannot be an empty string. " +
                        "Valid types are: $JS, $KTNB, $SVG, $PNG"
            )
        }

        val outputTypes = outputOption
            .split(",")
            .map { it.trim().lowercase() }
            .toSet()
            .also { types ->
                val validTypes = setOf(JS, KTNB, SVG, PNG)
                val invalidTypes = types - validTypes
                if (invalidTypes.isNotEmpty()) {
                    throw IllegalArgumentException(
                        "Invalid output type(s): ${invalidTypes.joinToString(", ")}. " +
                                "Valid types are: ${validTypes.joinToString(", ")}"
                    )
                }
            }

        addWebOutput = JS in outputTypes
        addKTNBOutput = KTNB in outputTypes
        addStaticSvg = SVG in outputTypes
        addStaticPng = PNG in outputTypes
    }

    override fun Builder.onLoaded() {
        import("org.jetbrains.letsPlot.*")
        import("org.jetbrains.letsPlot.geom.*")
        import("org.jetbrains.letsPlot.geom.extras.*")
        import("org.jetbrains.letsPlot.stat.*")
        import("org.jetbrains.letsPlot.label.*")
        import("org.jetbrains.letsPlot.scale.*")
        import("org.jetbrains.letsPlot.facet.*")
        import("org.jetbrains.letsPlot.sampling.*")
        import("org.jetbrains.letsPlot.export.*")
        import("org.jetbrains.letsPlot.tooltips.*")
        import("org.jetbrains.letsPlot.annotations.*")
        import("org.jetbrains.letsPlot.themes.*")
        import("org.jetbrains.letsPlot.font.*")
        import("org.jetbrains.letsPlot.coord.*")
        import("org.jetbrains.letsPlot.pos.*")
        import("org.jetbrains.letsPlot.interact.*")
        import("org.jetbrains.letsPlot.bistro.corr.*")
        import("org.jetbrains.letsPlot.bistro.qq.*")
        import("org.jetbrains.letsPlot.bistro.joint.*")
        import("org.jetbrains.letsPlot.bistro.residual.*")
        import("org.jetbrains.letsPlot.bistro.waterfall.*")
        import("org.jetbrains.letsPlot.intern.toSpec")
        import("org.jetbrains.letsPlot.spatial.SpatialDataset")

        onLoaded {
            val isolatedFrameParam = if (isolatedFrame.isNotEmpty()) isolatedFrame.toBoolean() else null
            frontendContext = LetsPlot.setupNotebook(lpJsVersion, isolatedFrameParam) { display(HTML(it), null) }
            LetsPlot.apiVersion = lpkVersion
            // Load library JS
            if (addWebOutput) {
                display(HTML(frontendContext.getConfigureHtml()), null)
            }
            // add figure renders AFTER frontendContext initialization
            addRenders()
            declare("letsPlotNotebookConfig" to config)

            LetsPlot.outputsDescription = NotebookRenderingContext.OutputOptions(
                addWebOutput = addWebOutput,
                addKTNBOutput = addKTNBOutput,
                addStaticSvg = addStaticSvg,
                addStaticPng = addStaticPng
            ).describe()
        }
    }


    private fun Builder.addRenders() {
        var firstFigureRendered = false

        if (addWebOutput) {
            resources {
                js("letsPlotJs") {
                    url(scriptUrl(lpJsVersion))
                }
            }
        }

        renderWithHost<Figure> { host, value ->
            // For cases when Integration is added via Kotlin Notebook project dependency;
            // display the "configure HTML" with the first `Figure` rendering
            if (addWebOutput && !firstFigureRendered) {
                firstFigureRendered = true
                host.execute { display(HTML(frontendContext.getConfigureHtml()), null) }
            }
            NotebookRenderingContext(
                config, frontendContext,
                NotebookRenderingContext.OutputOptions(
                    addWebOutput = addWebOutput,
                    addKTNBOutput = addKTNBOutput,
                    addStaticSvg = addStaticSvg,
                    addStaticPng = addStaticPng
                )
            ).figureToMimeResult(value)
        }
    }

}
