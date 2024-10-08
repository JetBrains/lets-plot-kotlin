package org.jetbrains.letsPlot.jupyter

import org.jetbrains.kotlinx.jupyter.api.*
import org.jetbrains.kotlinx.jupyter.api.libraries.ExecutionHost
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.LetsPlot
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper.scriptUrl
import org.jetbrains.letsPlot.export.VersionChecker
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext

@Suppress("unused")
internal class Integration(private val notebook: Notebook, private val options: MutableMap<String, String?>) :
    JupyterIntegration() {

    private val config = JupyterConfig()
    private lateinit var frontendContext: NotebookFrontendContext


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
        import("org.jetbrains.letsPlot.bistro.corr.*")
        import("org.jetbrains.letsPlot.bistro.qq.*")
        import("org.jetbrains.letsPlot.bistro.joint.*")
        import("org.jetbrains.letsPlot.bistro.residual.*")
        import("org.jetbrains.letsPlot.bistro.waterfall.*")
        import("org.jetbrains.letsPlot.intern.toSpec")
        import("org.jetbrains.letsPlot.spatial.SpatialDataset")


        onLoaded {
            // Take integration options from descriptor by default;
            // If used via Kotlin Notebook plugin as a dependency,
            // provide defaults (versions from `VersionChecker`
            // and empty `isolatedFrame`)
            val api = options["api"] ?: VersionChecker.letsPlotKotlinAPIVersion
            val js = options["js"] ?: VersionChecker.letsPlotJsVersion
            val isolatedFrame = options["isolatedFrame"] ?: ""
            val isolatedFrameParam = if (isolatedFrame.isNotEmpty()) isolatedFrame.toBoolean() else null
            frontendContext = LetsPlot.setupNotebook(js, isolatedFrameParam) { display(HTML(it), null) }
            LetsPlot.apiVersion = api
            // Load library JS
            display(HTML(frontendContext.getConfigureHtml()), null)
            // add figure renders AFTER frontendContext initialization
            addRenders(js)
            declare("letsPlotNotebookConfig" to config)
        }
    }


    private fun Builder.addRenders(jsVersion: String) {
        var firstFigureRendered = false
        resources {
            js("letsPlotJs") {
                url(scriptUrl(jsVersion))
            }
        }
        renderWithHostTemp<Figure> { host, value ->
            // For cases when Integration is added via Kotlin Notebook project dependency;
            // display configure HTML with the first `Figure rendering
            if (!firstFigureRendered) {
                firstFigureRendered = true
                host.execute { display(HTML(frontendContext.getConfigureHtml()), null) }
            }
            NotebookRenderingContext(config, frontendContext).figureToMimeResult(value)
        }
    }


    // copy-pasted from jupyter api;
    // it is not possible to use `renderWithHost` directly because it is inline and built with JVM 11
    private inline fun <reified T : Any> Builder.renderWithHostTemp(noinline renderer: CodeCell.(ExecutionHost, T) -> Any) {
        val execution =
            ResultHandlerExecution { host, property ->
                val currentCell = notebook.currentCell
                    ?: throw IllegalStateException("Current cell should not be null on renderer invocation")
                FieldValue(renderer(currentCell, host, property.value as T), null)
            }
        addRenderer(SubtypeRendererTypeHandler(T::class, execution))
    }

}
