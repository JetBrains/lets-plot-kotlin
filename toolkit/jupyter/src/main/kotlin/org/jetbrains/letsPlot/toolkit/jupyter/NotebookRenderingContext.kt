package org.jetbrains.letsPlot.toolkit.jupyter

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.jetbrains.kotlinx.jupyter.api.MimeTypedResultEx
import org.jetbrains.kotlinx.jupyter.api.MimeTypes
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.toolkit.jupyter.json.extendedByJson
import org.jetbrains.letsPlot.toolkit.json.serializeJsonMap
import java.util.*

internal class NotebookRenderingContext(
    private val config: JupyterConfig,
    private val frontendContext: NotebookFrontendContext,
    private val webOnly: Boolean
) {
    /**
     * Creates Mime JSON with two output options - HTML and application/plot.
     * The HTML output is used in Jupyter Notebooks and Datalore (the other one is ignored).
     * The application/plot is used in Kotlin Notebook when native rendering via Swing is enabled.
     */
    private fun figureToMimeJson(figure: Figure): JsonObject {
        val spec = figure.toSpec()
        val html = frontendContext.getDisplayHtml(figure.toSpec())
        return buildJsonObject {
            put(MimeTypes.HTML, JsonPrimitive(html))
            if (!webOnly) {
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
    private fun figureToHiddenSvg(figure: Figure): Map<String, JsonPrimitive> {
        val plotSVG = PlotSvgExport.buildSvgImageFromRawSpecs(figure.toSpec())
        val id = UUID.randomUUID().toString()
        val svgWithID = with(plotSVG) {
            val svgSplit = split('\n')
            (listOf(updateSvg(svgSplit.first(), id)) + svgSplit.drop(1)).joinToString("\n")
        }
        val extraHTML = """
                $svgWithID
                <script>document.getElementById("$id").style.display = "none";</script>
                """.trimIndent()

        return mapOf(MimeTypes.HTML to JsonPrimitive(extraHTML))
    }

    fun figureToMimeResult(figure: Figure): MimeTypedResultEx {
        val basicResult = figureToMimeJson(figure)
        val extraSvg = figureToHiddenSvg(figure)
        return MimeTypedResultEx(
            basicResult extendedByJson extraSvg,
            id = null,
            metadataModifiers = emptyList()
        )
    }
}