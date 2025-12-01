package org.jetbrains.letsPlot.toolkit.jupyter

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.jetbrains.kotlinx.jupyter.api.MimeTypedResultEx
import org.jetbrains.kotlinx.jupyter.api.MimeTypes
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.core.plot.export.PlotImageExport
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.toolkit.json.serializeJsonMap
import org.jetbrains.letsPlot.toolkit.jupyter.json.extendedByJson
import java.util.*

internal class NotebookRenderingContext(
    private val config: JupyterConfig,
    private val frontendContext: NotebookFrontendContext,
    private val outputOptions: OutputOptions
) {

    data class OutputOptions(
        val addWebOutput: Boolean,
        val addKTNBOutput: Boolean,
        val addStaticSvg: Boolean,
        val addStaticPng: Boolean,
    ) {
        fun hasInteractiveOutput(): Boolean {
            return addWebOutput || addKTNBOutput
        }

        fun describe() : String {
            val outputs = mutableListOf<String>()
            val hasInteractive = hasInteractiveOutput()
            if (addWebOutput) outputs.add("Web (HTML+JS)")
            if (addKTNBOutput) outputs.add("Kotlin Notebook (Swing)")
            if (addStaticSvg) outputs.add("Static SVG${if (hasInteractive) " (hidden)" else ""}")
            if (addStaticPng) outputs.add("Static PNG${if (hasInteractive) " (hidden)" else ""}")
            return if (outputs.isEmpty()) {
                "No outputs"
            } else {
                outputs.joinToString(", ")
            }
        }
    }

    /**
     * Creates Mime JSON with two output options - HTML and application/plot.
     * The HTML output is used in Jupyter Notebooks and Datalore (the other one is ignored).
     * The application/plot is used in Kotlin Notebook when native rendering via Swing is enabled.
     */
    private fun figureToMimeJson(figure: Figure): JsonObject {
        val spec = figure.toSpec()
        return buildJsonObject {
            if (outputOptions.addWebOutput) {
                val plotHtml = frontendContext.getDisplayHtml(spec)
                put(MimeTypes.HTML, JsonPrimitive(plotHtml))
            }
            if (outputOptions.addKTNBOutput) {
                put("application/plot+json", buildJsonObject {
                    put("output_type", JsonPrimitive("lets_plot_spec"))
                    put("output", serializeJsonMap(spec))
                    put("apply_color_scheme", JsonPrimitive(config.themeApplied))
                    put("swing_enabled", JsonPrimitive(config.swingEnabled))
                })
            }
        }
    }

    /**
     * Modifies an SVG by adding a new identifier and setting the width and height of the SVG to 100%,
     * while retaining the original dimensions in the style for responsiveness.
     * It also adds viewBox and preserveAspectRatio attributes to ensure the SVG scales properly without distortion.
     * Updates SVG id with provided one.
     */
    private fun updateSvg(svgString: String, id: String): String {
        val regex = Regex("""width=["']([^"']*)["']\s*height=["']([^"']*)["']""")
        return regex.replace(svgString) {
            val currentWidth = it.groupValues[1]
            val currentHeight = it.groupValues[2]
            """id=$id width="100%" height="100%" style="max-width: ${currentWidth}px; max-height: ${currentHeight}px;" viewBox="0 0 $currentWidth $currentHeight" preserveAspectRatio="xMinYMin meet""""
        }
    }

    /**
     * Converts a `Figure` object into a hidden SVG embedded in an HTML <figure> element.
     * The SVG is made hidden using an inline <script> tag. This script does not execute on platforms
     * like GitHub or Gist, allowing the output to be displayed statically on those platforms.
     */
    private fun figureToSvgOutput(figure: Figure, hidden: Boolean): Map<String, JsonPrimitive> {
        val plotSVG = PlotSvgExport.buildSvgImageFromRawSpecs(figure.toSpec())
        val id = UUID.randomUUID().toString()
        val svgWithID = with(plotSVG) {
            val svgSplit = split('\n')
            (listOf(updateSvg(svgSplit.first(), id)) + svgSplit.drop(1)).joinToString("\n")
        }

        val styleDisplayNone = if (hidden) {
            """<script>document.getElementById("$id").style.display = "none";</script>"""
        } else {
            ""
        }
        val htmlWithSvg = """
                $svgWithID
                $styleDisplayNone
                """.trimIndent()

        return mapOf(MimeTypes.HTML to JsonPrimitive(htmlWithSvg))
    }

    /**
     * Converts a `Figure` object into a hidden PNG embedded in an HTML <img> element.
     * The PNG is made hidden using an inline <script> tag. This allows the output to be displayed
     * statically on platforms like GitHub or Gist.
     */
    private fun figureToPngOutput(figure: Figure, hidden: Boolean): Map<String, JsonPrimitive> {
        val imageData = PlotImageExport.buildImageFromRawSpecs(
            plotSpec = figure.toSpec(),
            format = PlotImageExport.Format.PNG,
            scalingFactor = 2.0
        )
        val base64 = Base64.getEncoder().encodeToString(
            imageData.bytes
        )
        val width = imageData.plotSize.x.toInt()
        val height = imageData.plotSize.y.toInt()
        val id = UUID.randomUUID().toString()

        val styleDisplayNone = if (hidden) {
            """<script>document.getElementById("$id").style.display = "none";</script>"""
        } else {
            ""
        }
        val htmlWithPng = """
            <img id="$id" src="data:image/png;base64,$base64" alt="image" width="$width" height="$height">
            $styleDisplayNone
        """.trimIndent()

        return mapOf(MimeTypes.HTML to JsonPrimitive(htmlWithPng))
    }

    fun figureToMimeResult(figure: Figure): MimeTypedResultEx {
        // Hide static images if interactive outputs are present
        val hidden = outputOptions.hasInteractiveOutput()
        val mimeJson = figureToMimeJson(figure)
            .let {
                if (outputOptions.addStaticSvg) it.extendedByJson(figureToSvgOutput(figure, hidden)) else it
            }.let {
                if (outputOptions.addStaticPng) it.extendedByJson(figureToPngOutput(figure, hidden)) else it
            }
        return MimeTypedResultEx(
            mimeJson,
            id = null,
            metadataModifiers = emptyList()
        )
    }
}